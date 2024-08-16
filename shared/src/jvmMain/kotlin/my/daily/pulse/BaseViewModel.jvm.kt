package my.daily.pulse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual open class BaseViewModel {
    private val job = SupervisorJob()

    actual val scope: CoroutineScope
        get() = CoroutineScope(job + Dispatchers.Main)

    fun clear() {
        scope.cancel()
    }
}