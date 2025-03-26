package app.linuxshaman.undermeetup0.ui.view.buy.pro

import android.view.View
import android.view.ViewGroup
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.databinding.WidgetBuyProBinding
import app.linuxshaman.undermeetup0.ui.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.util.inflate
import app.linuxshaman.undermeetup0.ui.view.ScreenWidgetViewHolder
import app.linuxshaman.undermeetup0.ui.widget.ScreenWidget
import app.linuxshaman.undermeetup0.ui.widget.buy.pro.BuyProWidget
import app.linuxshaman.undermeetup0.ui.widget.buy.pro.BuyProWidgetPayload
import com.bumptech.glide.RequestManager

/**
 * Created by linuxshaman on 26.03.2025.
 */
class BuyProWidgetViewHolder(
    parent: ViewGroup,
    callbackManager: UICallbackManager
) : ScreenWidgetViewHolder(
    parent.inflate(R.layout.widget_buy_pro),
    callbackManager
) {

    private val binding = WidgetBuyProBinding.bind(itemView)


    override fun bind(widget: ScreenWidget) {
        widget as BuyProWidget

        binding.title.setText(widget.title)
        binding.subtitle.setText(widget.subtitle)

        val context = binding.root.context
        binding.subscribe.text = context.getString(R.string.subscribe_format, widget.price)

        binding.subscribe.setOnClickListener {
            callbackManager.postAction(widget.onSubscribeAction)
        }

        binding.cancel.setOnClickListener {
            callbackManager.postAction(widget.onCancelAction)
        }
    }

    override fun bind(widget: ScreenWidget, payload: Any?) {
        widget as BuyProWidget
        payload as BuyProWidgetPayload

        if (payload.isTitleChanged) {
            binding.title.setText(widget.title)
        }
        if (payload.isSubtitleChanged) {
            binding.subtitle.setText(widget.subtitle)
        }

        if (payload.isPriceChanged) {
            val context = binding.root.context
            binding.subscribe.text = context.getString(R.string.subscribe_format, widget.price)
        }

        if (payload.isOnSubscribeActionChanged) {
            binding.subscribe.setOnClickListener {
                callbackManager.postAction(widget.onSubscribeAction)
            }
        }

        if (payload.isonCancelActionChanged) {
            binding.cancel.setOnClickListener {
                callbackManager.postAction(widget.onCancelAction)
            }
        }
    }
}