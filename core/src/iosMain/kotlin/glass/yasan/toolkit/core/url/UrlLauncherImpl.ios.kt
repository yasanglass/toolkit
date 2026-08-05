package glass.yasan.toolkit.core.url

import glass.yasan.toolkit.core.annotation.InternalToolkitApi
import glass.yasan.toolkit.core.app.ToolkitApp
import glass.yasan.toolkit.core.tracking.withUtmSourceParameter
import glass.yasan.toolkit.core.url.UrlLaunchResult.Failure.Error
import glass.yasan.toolkit.core.url.UrlLaunchResult.Failure.InvalidUrl
import glass.yasan.toolkit.core.url.UrlLaunchResult.Failure.Unsupported
import glass.yasan.toolkit.core.url.UrlLaunchResult.Success
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import kotlin.coroutines.resume

public actual class UrlLauncherImpl(
    private val toolkitApp: ToolkitApp,
) : UrlLauncher {

    @OptIn(InternalToolkitApi::class)
    actual override suspend fun launch(url: String): UrlLaunchResult =
        try {
            val trackedUrl = url.withUtmSourceParameter(toolkitApp)
                ?: return InvalidUrl
            val nsUrl = NSURL.URLWithString(
                URLString = trackedUrl.toString(),
            ) ?: return InvalidUrl

            suspendCancellableCoroutine { continuation ->
                UIApplication.sharedApplication.openURL(
                    nsUrl,
                    options = emptyMap<Any?, Any>(),
                ) { success ->
                    if (success) {
                        continuation.resume(Success)
                    } else {
                        continuation.resume(Unsupported)
                    }
                }
            }
        } catch (e: Exception) {
            Error(e)
        }
}
