package app.linuxshaman.undermeetup0.ui.core.tools

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.ui.core.widget.ViewTypes
import app.linuxshaman.undermeetup0.ui.util.getDimensionPixelSize
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */
@FragmentScoped
class WidgetListDecoration @Inject constructor(
    @ActivityContext private val context: Context
) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        var left = 0
        var top = 0
        var right = 0
        var bottom = 0

        val viewHolder = parent.findContainingViewHolder(view)
        if (viewHolder != null) {
            val position = viewHolder.adapterPosition
            if (position >= 0) {
                val type = parent.adapter?.getItemViewType(position)
                if (type != null) {
                    val viewType = ViewTypes.entries[type]
                    when (viewType) {
                        ViewTypes.Button -> {
                            val offset = context.getDimensionPixelSize(R.dimen.offset_l)
                            top = offset * 5
                            left = offset
                            right = offset
                        }

                        ViewTypes.Loading, ViewTypes.Gallery, ViewTypes.GalleryItem -> {
                            //unused
                        }
                    }
                }
            }
        }
        outRect.set(left, top, right, bottom)
    }

}