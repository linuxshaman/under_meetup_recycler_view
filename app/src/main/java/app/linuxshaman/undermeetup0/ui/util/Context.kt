package app.linuxshaman.undermeetup0.ui.util

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.DimenRes

/**
 * Created by linuxshaman on 23.03.2025.
 */

val Context.layoutInflater: LayoutInflater
    get() {
        return LayoutInflater.from(this)
    }


fun Context.getDimension(@DimenRes res: Int): Float {
    return resources.getDimension(res)
}

fun Context.getDimensionPixelSize(@DimenRes res: Int): Int {
    return resources.getDimensionPixelSize(res)
}