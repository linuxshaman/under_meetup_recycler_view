package app.linuxshaman.undermeetup0.ui.view.gallery

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import app.linuxshaman.undermeetup0.ui.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.widget.ViewTypes
import app.linuxshaman.undermeetup0.ui.widget.gallery.GalleryItem
import app.linuxshaman.undermeetup0.ui.widget.gallery.GalleryItemDiffUtilCallback
import app.linuxshaman.undermeetup0.ui.widget.gallery.GalleryItemPayload
import com.bumptech.glide.RequestManager

/**
 * Created by linuxshaman on 24.03.2025.
 */
class GalleryItemAdapter(
    private val requestManager: RequestManager,
    private val callbackManager: UICallbackManager
) : RecyclerView.Adapter<GalleryItemViewHolder>() {

    private val differ = AsyncListDiffer(this, GalleryItemDiffUtilCallback())

    fun setItems(items: List<GalleryItem>) {
        differ.submitList(items)
    }

    override fun getItemViewType(position: Int): Int {
        return ViewTypes.GalleryItem.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        return GalleryItemViewHolder(parent, requestManager, callbackManager)
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