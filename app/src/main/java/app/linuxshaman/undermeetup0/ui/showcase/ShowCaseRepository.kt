package app.linuxshaman.undermeetup0.ui.showcase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by linuxshaman on 23.03.2025.
 */
@Singleton
class ShowCaseRepository @Inject constructor() {

    private val inflateCount = MutableStateFlow<Map<String, Int>>(mutableMapOf())
    private val mutex = Mutex()


    fun incrementInflateCount(viewName: String) = runBlocking {
        mutex.lock()
        try {
            val count = inflateCount.first().toMutableMap()
            if (count.containsKey(viewName)) {
                val viewTypeInflateCount = count[viewName]!! + 1
                count[viewName] = viewTypeInflateCount
            } else {
                count[viewName] = 1
            }
            inflateCount.emit(count)
        } finally {
            mutex.unlock()
        }
    }

    fun getCount(): Flow<Map<String, Int>> {
        return inflateCount
    }


}