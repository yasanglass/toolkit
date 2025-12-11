package glass.yasan.toolkit.core.url

import glass.yasan.toolkit.core.annotation.InternalToolkitApi

@InternalToolkitApi
public expect class UrlLauncherImpl : UrlLauncher {
    override fun launch(url: String): Boolean
}
