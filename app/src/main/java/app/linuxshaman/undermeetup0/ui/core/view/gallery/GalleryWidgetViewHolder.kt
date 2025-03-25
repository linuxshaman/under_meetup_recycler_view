package app.linuxshaman.undermeetup0.ui.core.view.gallery

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.databinding.WidgetGalleryBinding
import app.linuxshaman.undermeetup0.ui.core.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.core.tools.ScreenWidgetRecycledViewPool
import app.linuxshaman.undermeetup0.ui.core.view.ScreenWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.core.widget.ScreenWidget
import app.linuxshaman.undermeetup0.ui.core.widget.gallery.GalleryWidget
import app.linuxshaman.undermeetup0.ui.core.widget.gallery.GalleryWidgetPayload
import app.linuxshaman.undermeetup0.ui.showcase.ShowCaseRepository
import app.linuxshaman.undermeetup0.ui.util.inflate
import com.bumptech.glide.RequestManager

/**
 * Created by linuxshaman on 24.03.2025.
 */
class GalleryWidgetViewHolder(
    parent: ViewGroup,
    callbackManager: UICallbackManager,
    showCaseRepository: ShowCaseRepository,
    private val requestManager: RequestManager,
    viewPool: ScreenWidgetRecycledViewPool
) : ScreenWidgetViewHolder(
    parent.inflate(R.layout.widget_gallery),
    callbackManager,
    showCaseRepository
) {

    private val binding = WidgetGalleryBinding.bind(itemView)
    private val adapter = GalleryItemAdapter(requestManager, showCaseRepository)

    init {
        binding.items.adapter = adapter
        binding.items.setRecycledViewPool(viewPool)
        (binding.items.layoutManager as LinearLayoutManager).recycleChildrenOnDetach = true
    }


    override fun bind(widget: ScreenWidget) {
        widget as GalleryWidget
        binding.title.setText(widget.title)
        adapter.setItems(widget.items)
    }

    override fun bind(widget: ScreenWidget, payload: Any?) {
        widget as GalleryWidget
        payload as GalleryWidgetPayload

        if (payload.isTitleChanged) {
            binding.title.setText(widget.title)
        }
        if (payload.isItemsChanged) {
            adapter.setItems(widget.items)
        }
    }
}