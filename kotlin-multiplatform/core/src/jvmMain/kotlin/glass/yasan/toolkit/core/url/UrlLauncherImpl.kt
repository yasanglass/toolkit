package glass.yasan.toolkit.core.url

import co.touchlab.kermit.Logger
import glass.yasan.toolkit.core.url.UrlLauncher.Companion.ERROR_MESSAGE
import java.awt.Desktop
import java.net.URI

internal actual class UrlLauncherImpl : UrlLauncher {

    actual override fun launch(url: String): Boolean = try {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(URI(url))
            true
        } else {
            false
        }
    } catch (e: Exception) {
        Logger.e(e) { "$ERROR_MESSAGE: $url" }
        false
    }
}
