package app.linuxshaman.undermeetup0.ui.core.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.linuxshaman.undermeetup0.ui.core.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.core.widget.ScreenWidget
import app.linuxshaman.undermeetup0.ui.showcase.ShowCaseRepository

/**
 * Created by linuxshaman on 23.03.2025.
 */
abstract class ScreenWidgetViewHolder(
    itemView: View,
    protected val callbackManager: UICallbackManager,
    showCaseRepository: ShowCaseRepository
) : RecyclerView.ViewHolder(itemView) {

    init {
        showCaseRepository.incrementInflateCount(this::class.simpleName!!)
    }


    abstract fun bind(widget: ScreenWidget)

    abstract fun bind(widget: ScreenWidget, payload: Any?)


}