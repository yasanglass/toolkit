package glass.yasan.toolkit.core.url

import glass.yasan.toolkit.core.Window

public actual class PlatformUrlLauncherImpl : PlatformUrlLauncher {

    @OptIn(ExperimentalWasmJsInterop::class)
    actual override suspend fun launch(url: String): UrlLaunchResult = try {
        if (Window.open(url, target = "_blank") != null) {
            UrlLaunchResult.Success
        } else {
            UrlLaunchResult.Failure.Unsupported
        }
    } catch (e: Exception) {
        UrlLaunchResult.Failure.Error(e)
    }
}
