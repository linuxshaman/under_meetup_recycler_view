package app.linuxshaman.undermeetup0.ui.widget.image

import androidx.annotation.DrawableRes
import app.linuxshaman.undermeetup0.ui.widget.ScreenWidget

/**
 * Created by linuxshaman on 26.03.2025.
 */
data class FullScreenImageWidget(
    val id: Long,
    @DrawableRes val image: Int,
    val isNavigateUpEnabled: Boolean
) : ScreenWidget {


    override fun areItemsTheSame(other: ScreenWidget): Boolean {
        return if (other is FullScreenImageWidget) {
            this.id == other.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(other: ScreenWidget): Boolean {
        other as FullScreenImageWidget

        return this.image == other.image && this.isNavigateUpEnabled == other.isNavigateUpEnabled
    }

    override fun getChangePayload(other: ScreenWidget): Any {
        other as FullScreenImageWidget

        return FullScreenImageWidgetPayload(
            isImageChanged = this.image != other.image,
            isNavigateUpChanged = this.isNavigateUpEnabled != other.isNavigateUpEnabled
        )
    }
}