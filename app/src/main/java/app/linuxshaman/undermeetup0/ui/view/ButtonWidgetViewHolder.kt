package app.linuxshaman.undermeetup0.ui.view

import android.view.ViewGroup
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.databinding.WidgetButtonBinding
import app.linuxshaman.undermeetup0.ui.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.util.inflate
import app.linuxshaman.undermeetup0.ui.widget.ScreenWidget
import app.linuxshaman.undermeetup0.ui.widget.button.ButtonWidget
import app.linuxshaman.undermeetup0.ui.widget.button.ButtonWidgetPayload

/**
 * Created by linuxshaman on 23.03.2025.
 */
class ButtonWidgetViewHolder(
    parent: ViewGroup,
    callbackManager: UICallbackManager
) : ScreenWidgetViewHolder(
    parent.inflate(R.layout.widget_button),
    callbackManager
) {

    private val binding = WidgetButtonBinding.bind(itemView)

    override fun bind(widget: ScreenWidget) {
        widget as ButtonWidget

        binding.button.setText(widget.text)
        binding.button.setOnClickListener {
            callbackManager.postAction(widget.action)
        }
    }

    override fun bind(widget: ScreenWidget, payload: Any?) {
        widget as ButtonWidget
        payload as ButtonWidgetPayload

        if (payload.isTextChanged) {
            binding.button.setText(widget.text)
        }

        if (payload.isActionChanged) {
            binding.button.setOnClickListener {
                callbackManager.postAction(widget.action)
            }
        }
    }
}