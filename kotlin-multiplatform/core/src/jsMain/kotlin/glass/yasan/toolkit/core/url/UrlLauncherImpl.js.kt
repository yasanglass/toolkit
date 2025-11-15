package glass.yasan.toolkit.core.url

import co.touchlab.kermit.Logger
import glass.yasan.toolkit.core.url.UrlLauncher.Companion.ERROR_MESSAGE
import kotlinx.browser.window

internal actual class UrlLauncherImpl : UrlLauncher {

    actual override fun launch(url: String): Boolean {
        return try {
            window.open(url, "_blank")
            true
        } catch (e: Exception) {
            Logger.e("$ERROR_MESSAGE: $url", e)
            false
        }
    }
}
