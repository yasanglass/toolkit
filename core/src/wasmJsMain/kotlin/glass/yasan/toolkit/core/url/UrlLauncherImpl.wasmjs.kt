package glass.yasan.toolkit.core.url

import co.touchlab.kermit.Logger
import glass.yasan.toolkit.core.Window
import glass.yasan.toolkit.core.url.UrlLauncher.Companion.ERROR_MESSAGE

public actual class UrlLauncherImpl : UrlLauncher {

    actual override fun launch(url: String): Boolean {
        return try {
            Window.open(url, target = "_blank")
            true
        } catch (e: Exception) {
            Logger.e(e) { "$ERROR_MESSAGE: $url" }
            false
        }
    }
}
