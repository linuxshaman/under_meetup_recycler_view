package app.linuxshaman.undermeetup0.ui.core.view

import android.view.View
import android.view.ViewGroup
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.ui.core.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.core.widget.ScreenWidget
import app.linuxshaman.undermeetup0.ui.showcase.ShowCaseRepository
import app.linuxshaman.undermeetup0.ui.util.inflate

/**
 * Created by linuxshaman on 23.03.2025.
 */
class LoadingWidgetViewHolder(
    parent: ViewGroup,
    callbackManager: UICallbackManager,
    showCaseRepository: ShowCaseRepository
) : ScreenWidgetViewHolder(
    parent.inflate(R.layout.widget_loading),
    callbackManager,
    showCaseRepository
) {
    override fun bind(widget: ScreenWidget) {
        //unused
    }

    override fun bind(widget: ScreenWidget, payload: Any?) {
        //unused
    }
}