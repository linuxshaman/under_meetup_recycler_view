package app.linuxshaman.undermeetup0.ui.widget.buy.pro

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import app.linuxshaman.undermeetup0.ui.action.UIAction
import app.linuxshaman.undermeetup0.ui.widget.ScreenWidget

/**
 * Created by linuxshaman on 26.03.2025.
 */
data class BuyProWidget(
    val id: Long,
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    val price: Int,
    val onSubscribeAction: UIAction,
    val onCancelAction: UIAction
) : ScreenWidget {


    override fun areItemsTheSame(other: ScreenWidget): Boolean {
        return if (other is BuyProWidget) {
            this.id == other.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(other: ScreenWidget): Boolean {
        other as BuyProWidget

        return this.title == other.title
                && this.subtitle == other.subtitle
                && this.price == other.price
                && this.onSubscribeAction == other.onSubscribeAction
                && this.onCancelAction != other.onCancelAction
    }

    override fun getChangePayload(other: ScreenWidget): Any {
        other as BuyProWidget
        return BuyProWidgetPayload(
            isTitleChanged = this.title != other.title,
            isSubtitleChanged = this.subtitle != other.subtitle,
            isPriceChanged = this.price != other.price,
            isOnSubscribeActionChanged = this.onSubscribeAction != other.onSubscribeAction,
            isonCancelActionChanged = this.onCancelAction != other.onCancelAction
        )
    }
}