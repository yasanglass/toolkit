package glass.yasan.toolkit.core.url

import glass.yasan.toolkit.core.url.UrlLauncher.Companion.ERROR_MESSAGE
import kotlinx.browser.window

public actual class UrlLauncherImpl : UrlLauncher {

    actual override fun launch(url: String): Boolean {
        return try {
            window.open(url, "_blank")
            true
        } catch (e: Exception) {
            console.error("$ERROR_MESSAGE: $url", e)
            false
        }
    }
}
