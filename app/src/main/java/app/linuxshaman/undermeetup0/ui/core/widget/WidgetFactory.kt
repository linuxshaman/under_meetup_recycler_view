package app.linuxshaman.undermeetup0.ui.core.widget

import android.view.ViewGroup
import app.linuxshaman.undermeetup0.ui.core.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.core.view.ButtonWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.core.view.LoadingWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.core.view.ScreenWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.core.widget.button.ButtonWidget
import app.linuxshaman.undermeetup0.ui.core.widget.loading.LoadingWidget
import app.linuxshaman.undermeetup0.ui.showcase.ShowCaseRepository
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */
@FragmentScoped
class WidgetFactory @Inject constructor(
    private val callbackManager: UICallbackManager,
    private val showCaseRepository: ShowCaseRepository
) {


    fun getItemViewType(item: ScreenWidget): Int {
        val viewType = when (item) {
            is LoadingWidget -> {
                ViewTypes.Loading
            }

            is ButtonWidget -> {
                ViewTypes.Button
            }

            else -> error("Unknown screen widget: $item")
        }
        return viewType.ordinal
    }

    fun create(parent: ViewGroup, viewType: Int): ScreenWidgetViewHolder {
        val type = ViewTypes.entries[viewType]
        return when (type) {
            ViewTypes.Loading -> {
                LoadingWidgetViewHolder(parent, callbackManager, showCaseRepository)
            }

            ViewTypes.Button -> {
                ButtonWidgetViewHolder(parent, callbackManager, showCaseRepository)
            }
        }
    }


}