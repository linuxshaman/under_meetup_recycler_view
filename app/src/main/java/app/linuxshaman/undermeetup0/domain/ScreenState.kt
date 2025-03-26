package app.linuxshaman.undermeetup0.domain

import app.linuxshaman.undermeetup0.ui.widget.ScreenWidget

/**
 * Created by linuxshaman on 26.03.2025.
 */
data class ScreenState(
    val widgets: List<ScreenWidget>,
    val isUpNavigationEnabled: Boolean
)