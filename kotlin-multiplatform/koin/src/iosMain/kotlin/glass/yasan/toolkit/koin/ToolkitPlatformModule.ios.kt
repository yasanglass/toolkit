package glass.yasan.toolkit.koin

import glass.yasan.toolkit.core.url.UrlLauncher
import glass.yasan.toolkit.core.url.UrlLauncherImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

public actual val toolkitPlatformModule: Module = module {
    factoryOf<UrlLauncher>(::UrlLauncherImpl)
}
