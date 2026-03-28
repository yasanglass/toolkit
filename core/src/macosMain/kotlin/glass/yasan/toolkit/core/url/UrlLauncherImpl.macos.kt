package glass.yasan.toolkit.core.url

import platform.AppKit.NSWorkspace
import platform.Foundation.NSURL

public actual class UrlLauncherImpl : UrlLauncher {

    actual override fun launch(url: String): UrlLaunchResult =
        try {
            val nsUrl = NSURL.URLWithString(url) ?: return UrlLaunchResult.Failure.InvalidUrl
            if (NSWorkspace.sharedWorkspace.openURL(nsUrl)) {
                UrlLaunchResult.Success
            } else {
                UrlLaunchResult.Failure.Unsupported
            }
        } catch (e: Exception) {
            UrlLaunchResult.Failure.Error(e)
        }
}
