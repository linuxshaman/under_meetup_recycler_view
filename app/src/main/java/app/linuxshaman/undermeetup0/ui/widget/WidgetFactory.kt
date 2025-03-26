package app.linuxshaman.undermeetup0.ui.widget

import android.view.ViewGroup
import app.linuxshaman.undermeetup0.ui.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.tools.ScreenWidgetRecycledViewPool
import app.linuxshaman.undermeetup0.ui.view.ButtonWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.view.FullScreenImageViewHolder
import app.linuxshaman.undermeetup0.ui.view.LoadingWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.view.ScreenWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.view.buy.pro.BuyProWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.view.gallery.GalleryWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.widget.button.ButtonWidget
import app.linuxshaman.undermeetup0.ui.widget.buy.pro.BuyProWidget
import app.linuxshaman.undermeetup0.ui.widget.gallery.GalleryWidget
import app.linuxshaman.undermeetup0.ui.widget.image.FullScreenImageWidget
import app.linuxshaman.undermeetup0.ui.widget.loading.LoadingWidget
import com.bumptech.glide.RequestManager
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */
@FragmentScoped
class WidgetFactory @Inject constructor(
    private val callbackManager: UICallbackManager,
    private val requestManager: RequestManager,
    private val viewPool: ScreenWidgetRecycledViewPool,
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

            is FullScreenImageWidget -> {
                ViewTypes.FullScreenImage
            }

            is BuyProWidget -> {
                ViewTypes.BuyPro
            }

            else -> error("Unknown screen widget: $item")
        }
        return viewType.ordinal
    }

    fun create(parent: ViewGroup, viewType: Int): ScreenWidgetViewHolder {
        val type = ViewTypes.entries[viewType]
        return when (type) {
            ViewTypes.Loading -> {
                LoadingWidgetViewHolder(parent, callbackManager)
            }

            ViewTypes.Button -> {
                ButtonWidgetViewHolder(parent, callbackManager)
            }

            ViewTypes.Gallery -> {
                GalleryWidgetViewHolder(
                    parent,
                    callbackManager,
                    requestManager,
                    viewPool
                )
            }

            ViewTypes.FullScreenImage -> {
                FullScreenImageViewHolder(
                    parent, callbackManager, requestManager
                )
            }

            ViewTypes.BuyPro -> {
                BuyProWidgetViewHolder(parent, callbackManager)
            }

            ViewTypes.GalleryItem -> {
                error("Can't inflate gallery item")
            }
        }
    }
}