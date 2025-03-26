package app.linuxshaman.undermeetup0.ui.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.linuxshaman.undermeetup0.ui.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.widget.ScreenWidget

/**
 * Created by linuxshaman on 23.03.2025.
 */
abstract class ScreenWidgetViewHolder(
    itemView: View,
    protected val callbackManager: UICallbackManager
) : RecyclerView.ViewHolder(itemView) {


    abstract fun bind(widget: ScreenWidget)

    abstract fun bind(widget: ScreenWidget, payload: Any?)


}