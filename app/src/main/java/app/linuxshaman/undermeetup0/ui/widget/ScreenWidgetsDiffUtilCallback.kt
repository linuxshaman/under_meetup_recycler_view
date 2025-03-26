package app.linuxshaman.undermeetup0.ui.widget

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by linuxshaman on 23.03.2025.
 */
class ScreenWidgetsDiffUtilCallback : DiffUtil.ItemCallback<ScreenWidget>() {

    override fun areItemsTheSame(oldItem: ScreenWidget, newItem: ScreenWidget): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: ScreenWidget, newItem: ScreenWidget): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }

    override fun getChangePayload(oldItem: ScreenWidget, newItem: ScreenWidget): Any {
        return oldItem.getChangePayload(newItem)
    }
}