package app.linuxshaman.undermeetup0.ui.widget.gallery

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by linuxshaman on 24.03.2025.
 */
class GalleryItemDiffUtilCallback : DiffUtil.ItemCallback<GalleryItem>() {


    override fun areItemsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }

    override fun getChangePayload(oldItem: GalleryItem, newItem: GalleryItem): Any? {
        return oldItem.getChangePayload(newItem)
    }
}