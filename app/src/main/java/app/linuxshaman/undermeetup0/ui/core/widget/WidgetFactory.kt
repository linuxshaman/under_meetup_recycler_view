package app.linuxshaman.undermeetup0.ui.core.widget

import android.view.ViewGroup
import app.linuxshaman.undermeetup0.ui.core.view.ScreenWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.core.widget.button.ButtonWidget
import app.linuxshaman.undermeetup0.ui.core.widget.gallery.GalleryWidget
import app.linuxshaman.undermeetup0.ui.core.widget.loading.LoadingWidget
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */
@FragmentScoped
class WidgetFactory @Inject constructor(
    private val widgetSupplier: WidgetSupplier
) {


    fun getItemViewType(item: ScreenWidget): Int {
        val viewType = when (item) {
            is LoadingWidget -> {
                ViewTypes.Loading
            }

            is ButtonWidget -> {
                ViewTypes.Button
            }

            is GalleryWidget -> {
                ViewTypes.Gallery
            }

            else -> error("Unknown screen widget: $item")
        }
        return viewType.ordinal
    }

    fun create(parent: ViewGroup, viewType: Int): ScreenWidgetViewHolder {
        return widgetSupplier.create(parent, viewType)
    }
}