package app.linuxshaman.undermeetup0.ui.core.widget

/**
 * Created by linuxshaman on 23.03.2025.
 */
interface ScreenWidget {

    fun areItemsTheSame(other: ScreenWidget): Boolean
    fun areContentsTheSame(other: ScreenWidget): Boolean
    fun getChangePayload(other: ScreenWidget): Any


}