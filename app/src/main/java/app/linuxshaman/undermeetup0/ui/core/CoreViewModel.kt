package app.linuxshaman.undermeetup0.ui.core

import androidx.lifecycle.ViewModel
import app.linuxshaman.undermeetup0.ui.core.widget.ScreenWidget
import kotlinx.coroutines.flow.Flow

/**
 * Created by linuxshaman on 23.03.2025.
 */
abstract class CoreViewModel : ViewModel() {
    abstract val widgets: Flow<List<ScreenWidget>>
}