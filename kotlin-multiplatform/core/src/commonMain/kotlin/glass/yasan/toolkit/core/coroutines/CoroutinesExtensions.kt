package glass.yasan.toolkit.core.coroutines

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.isActive
import kotlin.coroutines.cancellation.CancellationException

public suspend fun Throwable.rethrowCancellationExceptionIfNeeded() {
    if (this is CancellationException && !currentCoroutineContext().isActive) {
        throw this
    }
}
