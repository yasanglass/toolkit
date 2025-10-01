package glass.yasan.toolkit.koin

import glass.yasan.toolkit.about.data.repository.AboutRepositoryImpl
import glass.yasan.toolkit.about.domain.repository.AboutRepository
import glass.yasan.toolkit.core.coroutines.ApplicationScope
import glass.yasan.toolkit.core.coroutines.DefaultDispatcherProvider
import glass.yasan.toolkit.core.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

public val toolkitModule: Module = module {
    singleOf<DispatcherProvider>(::DefaultDispatcherProvider)
    single<ApplicationScope> {
        CoroutineScope(SupervisorJob() + get<DispatcherProvider>().default)
    }

    factoryOf<AboutRepository>(::AboutRepositoryImpl)
}
