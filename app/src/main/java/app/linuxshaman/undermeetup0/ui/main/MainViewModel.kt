package app.linuxshaman.undermeetup0.ui.main

import androidx.annotation.StringRes
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.ui.core.CoreViewModel
import app.linuxshaman.undermeetup0.ui.core.action.UIAction
import app.linuxshaman.undermeetup0.ui.core.widget.ScreenWidget
import app.linuxshaman.undermeetup0.ui.core.widget.button.ButtonWidget
import app.linuxshaman.undermeetup0.ui.core.widget.gallery.GalleryItem
import app.linuxshaman.undermeetup0.ui.core.widget.gallery.GalleryWidget
import app.linuxshaman.undermeetup0.ui.core.widget.loading.LoadingWidget
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
) : CoreViewModel() {

    private val images = listOf(
        R.drawable.pic_1,
        R.drawable.pic_2,
        R.drawable.pic_3,
        R.drawable.pic_4,
        R.drawable.pic_5,
        R.drawable.pic_6,
        R.drawable.pic_7,
    )

    override val widgets: Flow<List<ScreenWidget>>
        get() = flow {
            emit(listOf(LoadingWidget))
            delay(3000L)
            emit(
                listOf(
                    createGallery(0L, R.string.gallery_1),
                    ButtonWidget(
                        0L,
                        R.string.simple,
                        UIAction.Navigate(R.id.action_mainFragment_to_simpleListFragment)
                    ),
                    createGallery(1L, R.string.gallery_1),
                    ButtonWidget(
                        1L,
                        R.string.view_pool,
                        UIAction.Navigate(R.id.action_mainFragment_to_viewPoolFragment)
                    ),
                    createGallery(2L, R.string.gallery_1),
                    ButtonWidget(
                        2L,
                        R.string.background,
                        UIAction.Navigate(R.id.action_mainFragment_to_backgroundFragment)
                    ),
                    createGallery(3L, R.string.gallery_1),
                    ButtonWidget(
                        2L,
                        R.string.background,
                        UIAction.Navigate(R.id.action_mainFragment_to_backgroundFragment)
                    ),
                    createGallery(4L, R.string.gallery_1)
                )
            )
        }.flowOn(Dispatchers.IO).distinctUntilChanged()


    private suspend fun createGallery(
        id: Long,
        @StringRes title: Int
    ): GalleryWidget {
        val galleryImages = images.shuffled()

        return GalleryWidget(
            id = id,
            title = title,
            items = galleryImages.mapIndexed { index, image ->
                GalleryItem(index.toLong(), image)
            }
        )
    }


}