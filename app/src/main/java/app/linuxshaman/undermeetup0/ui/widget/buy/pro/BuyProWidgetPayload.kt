package app.linuxshaman.undermeetup0.ui.widget.buy.pro

/**
 * Created by linuxshaman on 26.03.2025.
 */
data class BuyProWidgetPayload(
    val isTitleChanged: Boolean,
    val isSubtitleChanged: Boolean,
    val isPriceChanged: Boolean,
    val isOnSubscribeActionChanged: Boolean,
    val isonCancelActionChanged: Boolean
)