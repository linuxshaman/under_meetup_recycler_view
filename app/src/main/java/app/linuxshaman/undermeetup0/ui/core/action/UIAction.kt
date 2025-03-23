package app.linuxshaman.undermeetup0.ui.core.action

import androidx.annotation.IdRes

/**
 * Created by linuxshaman on 23.03.2025.
 */
sealed interface UIAction {

    data class Navigate(
        @IdRes val destination: Int
    ) : UIAction
}