package app.linuxshaman.undermeetup0.ui.widget.loading

import app.linuxshaman.undermeetup0.ui.widget.ScreenWidget

/**
 * Created by linuxshaman on 23.03.2025.
 */
object LoadingWidget : ScreenWidget {
    override fun areItemsTheSame(other: ScreenWidget): Boolean {
        return other is LoadingWidget
    }

    override fun areContentsTheSame(other: ScreenWidget): Boolean {
        return true
    }

    override fun getChangePayload(other: ScreenWidget): Any {
        return Any()
    }

}