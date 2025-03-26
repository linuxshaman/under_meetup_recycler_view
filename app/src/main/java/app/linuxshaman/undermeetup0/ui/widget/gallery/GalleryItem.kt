package app.linuxshaman.undermeetup0.ui.widget.gallery

import androidx.annotation.DrawableRes
import app.linuxshaman.undermeetup0.ui.action.UIAction

/**
 * Created by linuxshaman on 24.03.2025.
 */
data class GalleryItem(
    val id: Long,
    @DrawableRes val image: Int,
    val action: UIAction
) {
    fun areItemsTheSame(other: GalleryItem): Boolean {
        return this.id == other.id
    }

    fun areContentsTheSame(other: GalleryItem): Boolean {
        return this.image == other.image && this.action == other.action
    }

    fun getChangePayload(other: GalleryItem): Any? {
        return GalleryItemPayload(
            isImageChanged = this.image != other.image,
            isActionChanged = this.action != other.action
        )
    }
}