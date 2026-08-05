package glass.yasan.toolkit.core.url

import glass.yasan.toolkit.core.annotation.InternalToolkitApi
import glass.yasan.toolkit.core.app.ToolkitApp
import glass.yasan.toolkit.core.coroutines.DispatcherProvider
import glass.yasan.toolkit.core.tracking.withUtmSourceParameter
import glass.yasan.toolkit.core.url.UrlLaunchResult.Failure.Error
import glass.yasan.toolkit.core.url.UrlLaunchResult.Failure.InvalidUrl
import glass.yasan.toolkit.core.url.UrlLaunchResult.Failure.Unsupported
import glass.yasan.toolkit.core.url.UrlLaunchResult.Success
import io.ktor.http.toURI
import kotlinx.coroutines.withContext
import java.awt.Desktop
import java.net.URISyntaxException

public actual class UrlLauncherImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val toolkitApp: ToolkitApp,
) : UrlLauncher {

    @OptIn(InternalToolkitApi::class)
    actual override suspend fun launch(url: String): UrlLaunchResult = try {
        val trackedUrl = url.withUtmSourceParameter(toolkitApp)

        if (trackedUrl == null) {
            InvalidUrl
        } else {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop()
                    .isSupported(Desktop.Action.BROWSE)
            ) {
                withContext(dispatcherProvider.io) {
                    Desktop.getDesktop().browse(trackedUrl.toURI())
                }
                Success
            } else {
                Unsupported
            }
        }
    } catch (_: URISyntaxException) {
        InvalidUrl
    } catch (_: IllegalArgumentException) {
        InvalidUrl
    } catch (e: Exception) {
        Error(e)
    }
}
