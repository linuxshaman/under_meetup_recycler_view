package app.linuxshaman.undermeetup0.ui.tools

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.ui.util.getDimensionPixelSize
import app.linuxshaman.undermeetup0.ui.widget.ViewTypes
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

    private val defaultOffset by lazy(LazyThreadSafetyMode.NONE) {
        context.getDimensionPixelSize(R.dimen.offset_l)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        var left = 0
        var top = 0
        var right = 0

        val viewHolder = parent.findContainingViewHolder(view)
        if (viewHolder != null) {
            val position = viewHolder.absoluteAdapterPosition
            if (position >= 0) {
                val type = parent.adapter?.getItemViewType(position)
                if (type != null) {
                    val viewType = ViewTypes.entries[type]
                    when (viewType) {
                        ViewTypes.Button -> {
                            top = defaultOffset
                            left = defaultOffset
                            right = defaultOffset
                        }

                        ViewTypes.Gallery -> {
                            top = defaultOffset
                        }

                        ViewTypes.BuyPro -> {
                            left = defaultOffset
                            right = defaultOffset
                        }

                        else -> {
                            //skip
                        }
                    }
                }
            }
        }
        outRect.set(left, top, right, 0)
    }

}