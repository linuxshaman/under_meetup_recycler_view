package app.linuxshaman.undermeetup0.ui.util

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Created by linuxshaman on 23.03.2025.
 */

fun ViewGroup.inflate(
    @LayoutRes layout: Int
): View {
    return this.context.layoutInflater.inflate(layout, this, false)
}