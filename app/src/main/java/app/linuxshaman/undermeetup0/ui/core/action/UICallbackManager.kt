package app.linuxshaman.undermeetup0.ui.core.action

import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */
@FragmentScoped
class UICallbackManager @Inject constructor() : UICallbackOwner() {

    private val callbacks = mutableSetOf<LifecycleCallback>()

    fun postAction(action: UIAction, deferUntilActive: Boolean = false) {
        callbacks.forEach { callback ->
            callback.invoke(action, deferUntilActive)
        }
    }

    fun addCallback(owner: LifecycleOwner, callback: UICallback) {
        callbacks.add(LifecycleCallback(owner, callback, this))
    }

    override fun deleteCallback(callback: LifecycleCallback) {
        callbacks.remove(callback)
    }


}