package app.linuxshaman.undermeetup0.ui.action

import androidx.annotation.IdRes
import app.linuxshaman.undermeetup0.data.Destination

/**
 * Created by linuxshaman on 23.03.2025.
 */
sealed interface UIAction {

    data class Navigate(
        val destination: Destination
    ) : UIAction

    data object NavigateUp : UIAction

    data class Subscribe(
        val price: Int
    ) : UIAction

    data class SetBuyProAttempt(val attempt: Int) : UIAction
    data class MultiAction(val actions: List<UIAction>) : UIAction
}