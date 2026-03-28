package glass.yasan.toolkit.core.url

import java.awt.Desktop
import java.net.URI
import java.net.URISyntaxException

public actual class UrlLauncherImpl : UrlLauncher {

    actual override suspend fun launch(url: String): UrlLaunchResult = try {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(URI(url))
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
