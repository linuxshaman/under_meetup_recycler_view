package app.linuxshaman.undermeetup0.ui.tools

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import app.linuxshaman.undermeetup0.ui.view.ScreenWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.widget.ScreenWidget
import app.linuxshaman.undermeetup0.ui.widget.ScreenWidgetsDiffUtilCallback
import app.linuxshaman.undermeetup0.ui.widget.WidgetFactory
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */
@FragmentScoped
class ScreenWidgetAdapter @Inject constructor(
    private val widgetFactory: WidgetFactory,
) : RecyclerView.Adapter<ScreenWidgetViewHolder>() {

    private val differ = AsyncListDiffer(this, ScreenWidgetsDiffUtilCallback())


    fun setWidgets(widgets: List<ScreenWidget>) {
        differ.submitList(widgets)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return widgetFactory.getItemViewType(differ.currentList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenWidgetViewHolder {
        return widgetFactory.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: ScreenWidgetViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun onBindViewHolder(
        holder: ScreenWidgetViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            payloads.forEach { payload ->
                holder.bind(differ.currentList[position], payload)
            }
        }
    }
}