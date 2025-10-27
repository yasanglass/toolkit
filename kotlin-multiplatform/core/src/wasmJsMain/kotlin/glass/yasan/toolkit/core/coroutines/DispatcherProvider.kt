package glass.yasan.toolkit.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

public actual class DefaultDispatcherProvider : DispatcherProvider {
    actual override val main: CoroutineDispatcher = Dispatchers.Main
    actual override val io: CoroutineDispatcher = Dispatchers.Main // Wasm doesn't have IO
    actual override val default: CoroutineDispatcher = Dispatchers.Default
    actual override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}

public actual fun createDefaultDispatcherProvider(): DefaultDispatcherProvider =
    DefaultDispatcherProvider()
