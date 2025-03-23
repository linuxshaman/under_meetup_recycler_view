package app.linuxshaman.undermeetup0.ui.core.widget.button

import android.widget.Button
import androidx.annotation.StringRes
import app.linuxshaman.undermeetup0.ui.core.action.UIAction
import app.linuxshaman.undermeetup0.ui.core.widget.ScreenWidget

/**
 * Created by linuxshaman on 23.03.2025.
 */
data class ButtonWidget(
    val id: Long,
    @StringRes val text: Int,
    val action: UIAction
) : ScreenWidget {

    override fun areItemsTheSame(other: ScreenWidget): Boolean {
        return if (other is ButtonWidget) {
            this.id == other.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(other: ScreenWidget): Boolean {
        other as ButtonWidget

        return this.text == other.text && this.action == other.action
    }

    override fun getChangePayload(other: ScreenWidget): Any {
        other as ButtonWidget

        return ButtonWidgetPayload(
            isTextChanged = this.text != other.text,
            isActionChanged = this.action != other.action
        )
    }
}