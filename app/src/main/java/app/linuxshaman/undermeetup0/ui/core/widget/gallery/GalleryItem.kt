package app.linuxshaman.undermeetup0.ui.core.widget.gallery

import androidx.annotation.DrawableRes

/**
 * Created by linuxshaman on 24.03.2025.
 */
data class GalleryItem(
    val id: Long,
    @DrawableRes val image: Int,
) {
    fun areItemsTheSame(other: GalleryItem): Boolean {
        return this.id == other.id
    }

    fun areContentsTheSame(other: GalleryItem): Boolean {
        return this.image == other.image
    }

    fun getChangePayload(other: GalleryItem): Any? {
        return GalleryItemPayload(
            isImageChanged = this.image != other.image
        )
    }
}