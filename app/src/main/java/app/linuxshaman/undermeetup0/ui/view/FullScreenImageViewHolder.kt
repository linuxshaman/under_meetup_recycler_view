package app.linuxshaman.undermeetup0.ui.view

import android.view.View
import android.view.ViewGroup
import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.databinding.WidgetFullScreenImageBinding
import app.linuxshaman.undermeetup0.ui.action.UIAction
import app.linuxshaman.undermeetup0.ui.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.util.inflate
import app.linuxshaman.undermeetup0.ui.widget.ScreenWidget
import app.linuxshaman.undermeetup0.ui.widget.image.FullScreenImageWidget
import app.linuxshaman.undermeetup0.ui.widget.image.FullScreenImageWidgetPayload
import com.bumptech.glide.RequestManager

/**
 * Created by linuxshaman on 26.03.2025.
 */
class FullScreenImageViewHolder(
    parent: ViewGroup,
    callbackManager: UICallbackManager,
    private val requestManager: RequestManager
) : ScreenWidgetViewHolder(
    parent.inflate(R.layout.widget_full_screen_image),
    callbackManager
) {
    private val binding = WidgetFullScreenImageBinding.bind(itemView)

    init {
        binding.backButton.setOnClickListener {
            callbackManager.postAction(UIAction.NavigateUp)
        }
    }


    override fun bind(widget: ScreenWidget) {
        widget as FullScreenImageWidget

        requestManager.clear(binding.image)
        requestManager.load(widget.image).into(binding.image)
        binding.backButton.visibility = if (widget.isNavigateUpEnabled) {
            View.VISIBLE
        } else {
            View.GONE
        }

    }

    override fun bind(widget: ScreenWidget, payload: Any?) {
        widget as FullScreenImageWidget
        payload as FullScreenImageWidgetPayload

        if (payload.isImageChanged) {
            requestManager.clear(binding.image)
            requestManager.load(widget.image).into(binding.image)
        }
        if (payload.isNavigateUpChanged) {
            binding.backButton.visibility = if (widget.isNavigateUpEnabled) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}