package app.linuxshaman.undermeetup0.ui.core.view.gallery

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.databinding.GalleryItemBinding
import app.linuxshaman.undermeetup0.ui.core.widget.gallery.GalleryItem
import app.linuxshaman.undermeetup0.ui.core.widget.gallery.GalleryItemPayload
import app.linuxshaman.undermeetup0.ui.showcase.ShowCaseRepository
import app.linuxshaman.undermeetup0.ui.util.inflate
import com.bumptech.glide.RequestManager

/**
 * Created by linuxshaman on 24.03.2025.
 */
class GalleryItemViewHolder(
    parent: ViewGroup,
    private val requestManager: RequestManager,
    showCaseRepository: ShowCaseRepository
) : RecyclerView.ViewHolder(
    parent.inflate(R.layout.gallery_item)
) {

    private val binding = GalleryItemBinding.bind(itemView)

    init {
        showCaseRepository.incrementInflateCount(this::class.simpleName!!)
    }


    fun bind(item: GalleryItem) {
        requestManager.clear(binding.image)
        requestManager.load(item.image).into(binding.image)
    }

    fun bind(item: GalleryItem, payload: GalleryItemPayload) {
        if (payload.isImageChanged) {
            requestManager.clear(binding.image)
            requestManager.load(item.image).into(binding.image)
        }
    }


}