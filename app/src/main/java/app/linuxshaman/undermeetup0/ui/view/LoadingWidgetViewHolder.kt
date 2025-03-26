package app.linuxshaman.undermeetup0.ui.view

import android.view.ViewGroup
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.ui.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.util.inflate
import app.linuxshaman.undermeetup0.ui.widget.ScreenWidget

/**
 * Created by linuxshaman on 23.03.2025.
 */
class LoadingWidgetViewHolder(
    parent: ViewGroup,
    callbackManager: UICallbackManager
) : ScreenWidgetViewHolder(
    parent.inflate(R.layout.widget_loading),
    callbackManager
) {
    override fun bind(widget: ScreenWidget) {
        //unused
    }

    override fun bind(widget: ScreenWidget, payload: Any?) {
        //unused
    }
}