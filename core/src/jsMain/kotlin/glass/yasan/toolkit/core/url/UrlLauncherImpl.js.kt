package glass.yasan.toolkit.core.url

import kotlinx.browser.window

public actual class UrlLauncherImpl : UrlLauncher {

    actual override fun launch(url: String): UrlLaunchResult = try {
        if (window.open(url, target = "_blank") != null) {
            UrlLaunchResult.Success
        } else {
            UrlLaunchResult.Failure.Unsupported
        }
    } catch (e: Exception) {
        UrlLaunchResult.Failure.Error(e)
    }
}
