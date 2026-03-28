package glass.yasan.toolkit.core.url

import glass.yasan.toolkit.core.Window

public actual class UrlLauncherImpl : UrlLauncher {

    actual override fun launch(url: String): UrlLaunchResult {
        return try {
            Window.open(url, target = "_blank")
            UrlLaunchResult.Success
        } catch (e: Exception) {
            UrlLaunchResult.Failure.Error(e)
        }
    }
}
