package glass.yasan.toolkit.core.url

import co.touchlab.kermit.Logger
import glass.yasan.toolkit.core.url.UrlLauncher.Companion.ERROR_MESSAGE
import platform.AppKit.NSWorkspace
import platform.Foundation.NSURL

public actual class UrlLauncherImpl : UrlLauncher {

    actual override fun launch(url: String): Boolean =
        try {
            val nsUrl = NSURL.URLWithString(url) ?: return false
            NSWorkspace.sharedWorkspace.openURL(nsUrl)
        } catch (e: Exception) {
            Logger.e(e) { "$ERROR_MESSAGE: $url" }
            false
        }
}
