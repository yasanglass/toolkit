package glass.yasan.toolkit.core.url

import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import kotlin.coroutines.resume

public actual class UrlLauncherImpl : UrlLauncher {

    actual override suspend fun launch(url: String): UrlLaunchResult =
        try {
            val nsUrl = NSURL.URLWithString(
                URLString = url,
            ) ?: return UrlLaunchResult.Failure.InvalidUrl

            suspendCancellableCoroutine { continuation ->
                UIApplication.sharedApplication.openURL(
                    nsUrl,
                    options = emptyMap<Any?, Any>(),
                ) { success ->
                    if (success) {
                        continuation.resume(UrlLaunchResult.Success)
                    } else {
                        continuation.resume(UrlLaunchResult.Failure.Unsupported)
                    }
                }
            }
        } catch (e: Exception) {
            UrlLaunchResult.Failure.Error(e)
        }
}
