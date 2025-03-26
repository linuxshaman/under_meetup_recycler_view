package app.linuxshaman.undermeetup0.ui.widget.gallery

import androidx.annotation.StringRes
import app.linuxshaman.undermeetup0.ui.widget.ScreenWidget

/**
 * Created by linuxshaman on 24.03.2025.
 */
data class GalleryWidget(
    val id: Long,
    @StringRes val title: Int,
    val items: List<GalleryItem>
) : ScreenWidget {

    override fun areItemsTheSame(other: ScreenWidget): Boolean {
        return if (other is GalleryWidget) {
            this.id == other.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(other: ScreenWidget): Boolean {
        other as GalleryWidget
        return this.items == other.items
    }

    override fun getChangePayload(other: ScreenWidget): Any {
        other as GalleryWidget
        return GalleryWidgetPayload(
            isTitleChanged = this.title != other.title,
            isItemsChanged = this.items != other.items
        )
    }
}