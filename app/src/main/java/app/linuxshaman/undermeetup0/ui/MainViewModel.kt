package app.linuxshaman.undermeetup0.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.linuxshaman.undermeetup0.data.Destination
import app.linuxshaman.undermeetup0.domain.MainInteractor
import app.linuxshaman.undermeetup0.domain.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: MainInteractor
) : ViewModel() {

    val state: Flow<ScreenState> get() = interactor.getScreenState()

    fun navigate(destination: Destination) = viewModelScope.launch(Dispatchers.Default) {
        interactor.setDestination(destination)
    }

    fun navigateUp() = viewModelScope.launch(Dispatchers.Default) {
        interactor.popBackstack()
    }

    fun setBuyProAttempt(attempt: Int) = viewModelScope.launch(Dispatchers.Default) {
        interactor.setBuyProAttempt(attempt)
    }

}