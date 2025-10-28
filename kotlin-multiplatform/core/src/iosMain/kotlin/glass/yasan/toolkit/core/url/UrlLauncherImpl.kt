package glass.yasan.toolkit.core.url

import co.touchlab.kermit.Logger
import glass.yasan.toolkit.core.url.UrlLauncher.Companion.ERROR_MESSAGE
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

public actual class UrlLauncherImpl : UrlLauncher {

    actual override fun launch(url: String): Boolean =
        try {
            val nsUrl = NSURL.URLWithString(url) ?: return false
            if (UIApplication.sharedApplication.canOpenURL(nsUrl)) {
                UIApplication.sharedApplication.openURL(nsUrl)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            Logger.e(e) { "$ERROR_MESSAGE: $url" }
            false
        }
}
