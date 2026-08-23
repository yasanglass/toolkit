package glass.yasan.toolkit.koin

import glass.yasan.toolkit.core.annotation.InternalToolkitApi
import glass.yasan.toolkit.core.coroutines.ApplicationScope
import glass.yasan.toolkit.core.coroutines.DispatcherProvider
import glass.yasan.toolkit.core.coroutines.createDefaultDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.Module
import org.koin.dsl.module

@OptIn(InternalToolkitApi::class)
public val toolkitModule: Module = module {
    includes(platformModule)

    single<DispatcherProvider> { createDefaultDispatcherProvider() }
    single<ApplicationScope> {
        CoroutineScope(SupervisorJob() + get<DispatcherProvider>().default)
    }
}
