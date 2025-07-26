package glass.yasan.toolkit.koin

import glass.yasan.toolkit.core.coroutines.DefaultDispatcherProvider
import glass.yasan.toolkit.core.coroutines.DispatcherProvider
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

public val toolkitModule: Module = module {
    singleOf<DispatcherProvider>(::DefaultDispatcherProvider)
}