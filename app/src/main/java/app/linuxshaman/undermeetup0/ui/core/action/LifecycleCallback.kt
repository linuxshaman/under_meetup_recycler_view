package app.linuxshaman.undermeetup0.ui.core.action

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

internal class LifecycleCallback(
    private val lifecycleOwner: LifecycleOwner,
    private val callback: UICallback,
    private val callbackOwner: UICallbackOwner
) : DefaultLifecycleObserver {

    private var isActive: Boolean = false

    private var deferredActions = mutableListOf<UIAction>()

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        isActive = true
        invokeDeferredActions()
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        isActive = false
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        lifecycleOwner.lifecycle.removeObserver(this)
        callbackOwner.deleteCallback(this)
    }

    fun invoke(action: UIAction, deferUntilActive: Boolean): Boolean {
        if (isActive) {
            callback.onUIAction(action)
            return true
        } else if (deferUntilActive) {
            deferredActions.add(action)
        }
        return false
    }

    private fun invokeDeferredActions() {
        val actionsToDelete = mutableListOf<UIAction>()
        deferredActions.forEach { action ->
            if (invoke(action, true)) {
                actionsToDelete.add(action)
            }
        }
        deferredActions.removeAll(actionsToDelete)
    }


}