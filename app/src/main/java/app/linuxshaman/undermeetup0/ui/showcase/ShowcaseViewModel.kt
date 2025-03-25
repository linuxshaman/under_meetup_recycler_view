package app.linuxshaman.undermeetup0.ui.showcase

import androidx.annotation.StringRes
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.ui.core.CoreViewModel
import app.linuxshaman.undermeetup0.ui.core.widget.ScreenWidget
import app.linuxshaman.undermeetup0.ui.core.widget.gallery.GalleryItem
import app.linuxshaman.undermeetup0.ui.core.widget.gallery.GalleryWidget
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.random.Random

/**
 * Created by linuxshaman on 24.03.2025.
 */
@HiltViewModel
class ShowcaseViewModel @Inject constructor() : CoreViewModel() {

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
            emit(
                listOf(
                    createGallery(0L, R.string.gallery_1),
                    createGallery(1L, R.string.gallery_2)
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