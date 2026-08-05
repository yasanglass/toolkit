package glass.yasan.toolkit.core.url

import glass.yasan.toolkit.core.Window
import glass.yasan.toolkit.core.annotation.InternalToolkitApi
import glass.yasan.toolkit.core.app.ToolkitApp
import glass.yasan.toolkit.core.tracking.withUtmSourceParameter
import glass.yasan.toolkit.core.url.UrlLaunchResult.Failure.Error
import glass.yasan.toolkit.core.url.UrlLaunchResult.Failure.InvalidUrl
import glass.yasan.toolkit.core.url.UrlLaunchResult.Failure.Unsupported
import glass.yasan.toolkit.core.url.UrlLaunchResult.Success

public actual class UrlLauncherImpl(
    private val toolkitApp: ToolkitApp,
) : UrlLauncher {

    @OptIn(ExperimentalWasmJsInterop::class, InternalToolkitApi::class)
    actual override suspend fun launch(url: String): UrlLaunchResult = try {
        val trackedUrl = url.withUtmSourceParameter(toolkitApp)
            ?: return InvalidUrl
        if (Window.open(trackedUrl.toString(), target = "_blank") != null) {
            Success
        } else {
            Unsupported
        }
    } catch (e: Exception) {
        Error(e)
    }
}
