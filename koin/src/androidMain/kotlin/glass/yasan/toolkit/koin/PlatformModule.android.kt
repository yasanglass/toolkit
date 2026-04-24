package glass.yasan.toolkit.koin

import glass.yasan.toolkit.core.url.PlatformUrlLauncher
import glass.yasan.toolkit.core.url.PlatformUrlLauncherImpl
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    factory<PlatformUrlLauncher> { PlatformUrlLauncherImpl(context = get()) }
}
