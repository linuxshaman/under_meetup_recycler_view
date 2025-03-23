package app.linuxshaman.undermeetup0.ui.background

import app.linuxshaman.undermeetup0.ui.core.CoreViewModel
import app.linuxshaman.undermeetup0.ui.core.widget.ScreenWidget
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */
@HiltViewModel
class BackgroundViewModel @Inject constructor() : CoreViewModel() {

    override val widgets: Flow<List<ScreenWidget>>
        get() = emptyFlow()


}