package glass.yasan.toolkit.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher

public interface DispatcherProvider {
    public val main: CoroutineDispatcher
    public val io: CoroutineDispatcher
    public val default: CoroutineDispatcher
    public val unconfined: CoroutineDispatcher
}

public expect class DefaultDispatcherProvider : DispatcherProvider {
    override val main: CoroutineDispatcher
    override val io: CoroutineDispatcher
    override val default: CoroutineDispatcher
    override val unconfined: CoroutineDispatcher
}

public expect fun createDefaultDispatcherProvider(): DefaultDispatcherProvider
