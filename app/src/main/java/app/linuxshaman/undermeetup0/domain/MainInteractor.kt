package app.linuxshaman.undermeetup0.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.data.Destination
import app.linuxshaman.undermeetup0.data.DestinationRepository
import app.linuxshaman.undermeetup0.ui.action.UIAction
import app.linuxshaman.undermeetup0.ui.widget.button.ButtonWidget
import app.linuxshaman.undermeetup0.ui.widget.buy.pro.BuyProWidget
import app.linuxshaman.undermeetup0.ui.widget.gallery.GalleryItem
import app.linuxshaman.undermeetup0.ui.widget.gallery.GalleryWidget
import app.linuxshaman.undermeetup0.ui.widget.image.FullScreenImageWidget
import app.linuxshaman.undermeetup0.ui.widget.loading.LoadingWidget
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transformLatest
import javax.inject.Inject

/**
 * Created by linuxshaman on 26.03.2025.
 */
@ViewModelScoped
class MainInteractor @Inject constructor(
    private val destinationRepository: DestinationRepository
) {

    private val mainScreenState = MutableStateFlow<ScreenState?>(null)
    private val buyProAttempt = MutableStateFlow(0)

    private val images = listOf(
        R.drawable.pic_1,
        R.drawable.pic_2,
        R.drawable.pic_3,
        R.drawable.pic_4,
        R.drawable.pic_5,
        R.drawable.pic_6,
        R.drawable.pic_7,
    )

    suspend fun setDestination(destination: Destination) {
        destinationRepository.setDestination(destination)
    }

    suspend fun popBackstack() {
        destinationRepository.popBackStack()
    }

    fun getScreenState(): Flow<ScreenState> {
        return destinationRepository.destination.transformLatest { currentDestination ->
            val state = when (currentDestination) {
                is Destination.Main -> {
                    mainScreen()
                }

                is Destination.Image -> {
                    image(currentDestination.res)
                }

                is Destination.BuyPro -> {
                    buyPro()
                }
            }
            emitAll(state)
        }
    }

    private fun buyPro(): Flow<ScreenState> {
        return buyProAttempt.transformLatest { attempt ->

            val subtitle: Int
            val price: Int
            val onCancelAction: UIAction

            when (attempt) {
                0 -> {
                    subtitle = R.string.only_today
                    price = 200
                    onCancelAction = UIAction.SetBuyProAttempt(1)
                }

                1 -> {
                    subtitle = R.string.please_subscribe
                    price = 150
                    onCancelAction = UIAction.SetBuyProAttempt(2)
                }

                else -> {
                    subtitle = R.string.i_dont_care
                    price = 42
                    onCancelAction = UIAction.NavigateUp
                }
            }

            emit(
                ScreenState(
                    widgets = listOf(
                        BuyProWidget(
                            id = 0L,
                            title = R.string.get_pro,
                            subtitle = subtitle,
                            price = price,
                            onSubscribeAction = UIAction.NavigateUp,
                            onCancelAction = onCancelAction
                        )
                    ),
                    isUpNavigationEnabled = true
                )
            )
        }
    }

    private fun image(res: Int): Flow<ScreenState> {
        return flow {
            emit(
                ScreenState(
                    widgets = listOf(
                        createImage(
                            id = 0,
                            image = res,
                            isNavigateUpEnabled = true
                        )
                    ),
                    isUpNavigationEnabled = true
                )
            )
        }
    }

    private fun mainScreen(): Flow<ScreenState> {
        return flow {
            var state = mainScreenState.first()
            if (state == null) {
                emit(
                    ScreenState(
                        widgets = listOf(LoadingWidget),
                        isUpNavigationEnabled = false
                    )
                )
                delay(1000L)
                state = ScreenState(
                    widgets = listOf(
                        createGallery(0L, R.string.gallery_1),
                        createButton(
                            0L,
                            R.string.get_pro,
                            UIAction.MultiAction(
                                actions = listOf(
                                    UIAction.SetBuyProAttempt(0),
                                    UIAction.Navigate(Destination.BuyPro)
                                )
                            )
                        )
                    ),
                    isUpNavigationEnabled = false
                )
                mainScreenState.emit(state)
            }
            emit(state)
        }
    }

    private suspend fun createGallery(
        id: Long,
        @StringRes title: Int
    ): GalleryWidget {
        val galleryImages = images.shuffled()

        return GalleryWidget(
            id = id,
            title = title,
            items = galleryImages.mapIndexed { index, image ->
                GalleryItem(
                    index.toLong(),
                    image,
                    UIAction.Navigate(Destination.Image(image))
                )
            }
        )
    }

    private suspend fun createImage(
        id: Long,
        @DrawableRes image: Int,
        isNavigateUpEnabled: Boolean
    ): FullScreenImageWidget {
        return FullScreenImageWidget(
            id = id,
            image = image,
            isNavigateUpEnabled = isNavigateUpEnabled
        )
    }

    private fun createButton(
        id: Long,
        @StringRes text: Int,
        action: UIAction
    ): ButtonWidget {
        return ButtonWidget(id, text, action)
    }

    suspend fun setBuyProAttempt(attempt: Int) {
        buyProAttempt.emit(attempt)
    }


}