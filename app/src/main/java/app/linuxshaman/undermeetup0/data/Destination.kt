package app.linuxshaman.undermeetup0.data

import androidx.annotation.DrawableRes

/**
 * Created by linuxshaman on 26.03.2025.
 */
sealed class Destination {
    data object Main : Destination()
    data class Image(@DrawableRes val res: Int) : Destination()
    data object BuyPro : Destination()
}