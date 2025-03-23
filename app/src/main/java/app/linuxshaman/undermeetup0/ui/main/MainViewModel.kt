package app.linuxshaman.undermeetup0.ui.main

import app.linuxshaman.undermeetup0.R
import app.linuxshaman.undermeetup0.ui.core.CoreViewModel
import app.linuxshaman.undermeetup0.ui.core.action.UIAction
import app.linuxshaman.undermeetup0.ui.core.widget.ScreenWidget
import app.linuxshaman.undermeetup0.ui.core.widget.button.ButtonWidget
import app.linuxshaman.undermeetup0.ui.core.widget.loading.LoadingWidget
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
) : CoreViewModel() {

    override val widgets: Flow<List<ScreenWidget>>
        get() = flow {
            emit(listOf(LoadingWidget))
            delay(2000L)
            emit(
                listOf(
                    ButtonWidget(
                        0L,
                        R.string.simple,
                        UIAction.Navigate(R.id.action_mainFragment_to_simpleListFragment)
                    ),
                    ButtonWidget(
                        1L,
                        R.string.view_pool,
                        UIAction.Navigate(R.id.action_mainFragment_to_viewPoolFragment)
                    ),
                    ButtonWidget(
                        2L,
                        R.string.background,
                        UIAction.Navigate(R.id.action_mainFragment_to_backgroundFragment)
                    )
                )
            )
        }.flowOn(Dispatchers.IO).distinctUntilChanged()


}