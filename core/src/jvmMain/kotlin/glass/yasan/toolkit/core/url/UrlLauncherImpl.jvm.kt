package glass.yasan.toolkit.core.url

import java.awt.Desktop
import java.net.URI

public actual class UrlLauncherImpl : UrlLauncher {

    actual override fun launch(url: String): UrlLaunchResult = try {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(URI(url))
            UrlLaunchResult.Success
        } else {
            UrlLaunchResult.Failure.Unsupported
        }
    } catch (e: Exception) {
        UrlLaunchResult.Failure.Error(e)
    }
}
