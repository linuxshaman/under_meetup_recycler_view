package app.linuxshaman.undermeetup0.ui.core.view.gallery

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import app.linuxshaman.undermeetup0.ui.core.tools.ScreenWidgetRecycledViewPool
import app.linuxshaman.undermeetup0.ui.core.widget.ViewTypes
import app.linuxshaman.undermeetup0.ui.core.widget.gallery.GalleryItem
import app.linuxshaman.undermeetup0.ui.core.widget.gallery.GalleryItemDiffUtilCallback
import app.linuxshaman.undermeetup0.ui.core.widget.gallery.GalleryItemPayload
import app.linuxshaman.undermeetup0.ui.showcase.ShowCaseRepository
import com.bumptech.glide.RequestManager

/**
 * Created by linuxshaman on 24.03.2025.
 */
class GalleryItemAdapter(
    private val requestManager: RequestManager,
    private val showCaseRepository: ShowCaseRepository
) : RecyclerView.Adapter<GalleryItemViewHolder>() {

    private val differ = AsyncListDiffer(this, GalleryItemDiffUtilCallback())

    fun setItems(items: List<GalleryItem>) {
        differ.submitList(items)
    }

    override fun getItemViewType(position: Int): Int {
        return ViewTypes.GalleryItem.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        return GalleryItemViewHolder(parent, requestManager, showCaseRepository)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun onBindViewHolder(
        holder: GalleryItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            payloads.forEach { payload ->
                holder.bind(differ.currentList[position], payload as GalleryItemPayload)
            }
        }

    }
}