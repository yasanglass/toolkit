package glass.yasan.toolkit.core.coroutines

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.isActive
import kotlin.coroutines.cancellation.CancellationException

/**
 * @return `true` if the given [Throwable] is a [CancellationException] and the current coroutine is no longer active.
 */
public suspend fun Throwable.isCancellationToRethrow(): Boolean =
    this is CancellationException && !currentCoroutineContext().isActive

/**
 * Rethrows if the given [Throwable] is a [CancellationException] and the current coroutine is no longer active.
 */
public suspend fun Throwable.rethrowCancellation() {
    if (isCancellationToRethrow()) throw this
}

/**
 * @return the encapsulated value or `null`,
 * but rethrows if the failure is the cancellation of the current coroutine.
 */
public suspend fun <T> Result<T>.getOrNullOrRethrowCancellation(): T? {
    exceptionOrNull()?.let { throwable -> if (throwable.isCancellationToRethrow()) throw throwable }
    return getOrNull()
}

/**
 * @return the encapsulated value or calls [onFailure],
 * but rethrows if the failure is the cancellation of the current coroutine.
 */
public suspend fun <T> Result<T>.getOrElseOrRethrowCancellation(onFailure: (Throwable) -> T): T =
    getOrElse { throwable ->
        if (throwable.isCancellationToRethrow()) throw throwable
        onFailure(throwable)
    }
