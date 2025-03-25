package app.linuxshaman.undermeetup0.ui.core.widget

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import app.linuxshaman.undermeetup0.ui.core.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.core.tools.ScreenWidgetRecycledViewPool
import app.linuxshaman.undermeetup0.ui.core.view.ButtonWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.core.view.LoadingWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.core.view.ScreenWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.core.view.gallery.GalleryWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.showcase.ShowCaseRepository
import com.bumptech.glide.RequestManager
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by linuxshaman on 24.03.2025.
 */

private const val MAX_VIEWS_COUNT = 3

@FragmentScoped
class WidgetSupplier @Inject constructor(
    @ActivityContext private val context: Context,
    private val callbackManager: UICallbackManager,
    private val showCaseRepository: ShowCaseRepository,
    private val requestManager: RequestManager,
    private val viewPool: ScreenWidgetRecycledViewPool
) {

    private val parentJob = SupervisorJob()
    private val scope = CoroutineScope(parentJob + Dispatchers.Default)

    fun onCreate() {
        populate()
    }

    fun onDestroy() {
        parentJob.cancelChildren()
    }

    private fun populate() = scope.launch {
        val parent = FrameLayout(context)
        ViewTypes.entries.forEach { viewType ->
            if (viewType != ViewTypes.Loading && viewType != ViewTypes.GalleryItem) {
                for (i in 0 until MAX_VIEWS_COUNT) {
                    val viewHolder = create(parent, viewType.ordinal)
                    withContext(Dispatchers.Main) {
                        viewPool.putRecycledView(viewHolder)
                    }
                }
            }
        }
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

            ViewTypes.Gallery -> {
                GalleryWidgetViewHolder(
                    parent,
                    callbackManager,
                    showCaseRepository,
                    requestManager,
                    viewPool
                )
            }

            ViewTypes.GalleryItem -> {
                error("Can't inflate gallery item")
            }
        }
    }
}