package glass.yasan.toolkit.core.url

import glass.yasan.toolkit.core.annotation.InternalToolkitApi

@InternalToolkitApi
public expect class PlatformUrlLauncherImpl : PlatformUrlLauncher {
    override suspend fun launch(url: String): UrlLaunchResult
}
