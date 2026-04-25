package glass.yasan.toolkit.core.url

import glass.yasan.toolkit.core.coroutines.DispatcherProvider
import kotlinx.coroutines.withContext
import java.awt.Desktop
import java.net.URI
import java.net.URISyntaxException

public actual class UrlLauncherImpl(
    private val dispatcherProvider: DispatcherProvider,
) : UrlLauncher {

    actual override suspend fun launch(url: String): UrlLaunchResult = try {
        val uri = URI(url)

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            withContext(dispatcherProvider.io) {
                Desktop.getDesktop().browse(uri)
            }
            UrlLaunchResult.Success
        } else {
            UrlLaunchResult.Failure.Unsupported
        }
    } catch (_: URISyntaxException) {
        UrlLaunchResult.Failure.InvalidUrl
    } catch (_: IllegalArgumentException) {
        UrlLaunchResult.Failure.InvalidUrl
    } catch (e: Exception) {
        UrlLaunchResult.Failure.Error(e)
    }
}
