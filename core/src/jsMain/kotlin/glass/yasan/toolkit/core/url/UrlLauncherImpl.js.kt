package glass.yasan.toolkit.core.url

import kotlinx.browser.window

public actual class UrlLauncherImpl : UrlLauncher {

    actual override fun launch(url: String): UrlLaunchResult {
        return try {
            window.open(url, "_blank")
            UrlLaunchResult.Success
        } catch (e: Exception) {
            UrlLaunchResult.Failure.Error(e)
        }
    }
}
