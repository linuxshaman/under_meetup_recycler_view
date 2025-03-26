package app.linuxshaman.undermeetup0.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by linuxshaman on 26.03.2025.
 */
@Singleton
class DestinationRepository @Inject constructor() {

    private val mutex = Mutex()


    private val backstack = MutableStateFlow<List<Destination>>(listOf(Destination.Main))
    val destination: Flow<Destination>
        get() = backstack.map {
            it.last()
        }


    suspend fun setDestination(destination: Destination) {
        guarded {
            val items = backstack.first().toMutableList()
            items.add(destination)
            backstack.emit(items)
        }
    }

    suspend fun popBackStack() {
        guarded {
            val items = backstack.first().toMutableList()
            if (items.size > 1) {
                items.removeAt(items.size - 1)
            }
            backstack.emit(items)
        }
    }

    private suspend fun <T> guarded(block: suspend () -> T) {
        mutex.lock()
        try {
            block()
        } finally {
            mutex.unlock()
        }
    }


}