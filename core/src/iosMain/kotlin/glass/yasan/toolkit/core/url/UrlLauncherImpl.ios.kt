package glass.yasan.toolkit.core.url

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

public actual class UrlLauncherImpl : UrlLauncher {

    actual override fun launch(url: String): UrlLaunchResult =
        try {
            val nsUrl = NSURL.URLWithString(
                URLString = url,
            ) ?: return UrlLaunchResult.Failure.InvalidUrl

            if (UIApplication.sharedApplication.canOpenURL(nsUrl)) {
                if (UIApplication.sharedApplication.openURL(nsUrl)) {
                    UrlLaunchResult.Success
                } else {
                    UrlLaunchResult.Failure.Unsupported
                }
            } else {
                UrlLaunchResult.Failure.Unsupported
            }
        } catch (e: Exception) {
            UrlLaunchResult.Failure.Error(e)
        }
}
