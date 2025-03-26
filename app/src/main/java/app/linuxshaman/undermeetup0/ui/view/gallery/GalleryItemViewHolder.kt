package app.linuxshaman.undermeetup0.ui.view.gallery

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.databinding.GalleryItemBinding
import app.linuxshaman.undermeetup0.ui.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.util.inflate
import app.linuxshaman.undermeetup0.ui.widget.gallery.GalleryItem
import app.linuxshaman.undermeetup0.ui.widget.gallery.GalleryItemPayload
import com.bumptech.glide.RequestManager

/**
 * Created by linuxshaman on 24.03.2025.
 */
class GalleryItemViewHolder(
    parent: ViewGroup,
    private val requestManager: RequestManager,
    private val callbackManager: UICallbackManager
) : RecyclerView.ViewHolder(
    parent.inflate(R.layout.gallery_item)
) {

    private val binding = GalleryItemBinding.bind(itemView)


    fun bind(item: GalleryItem) {
        requestManager.clear(binding.image)
        requestManager.load(item.image).into(binding.image)
        binding.image.setOnClickListener {
            callbackManager.postAction(item.action)
        }
    }

    fun bind(item: GalleryItem, payload: GalleryItemPayload) {
        if (payload.isImageChanged) {
            requestManager.clear(binding.image)
            requestManager.load(item.image).into(binding.image)
        }
        if (payload.isActionChanged) {
            binding.image.setOnClickListener {
                callbackManager.postAction(item.action)
            }
        }
    }


}