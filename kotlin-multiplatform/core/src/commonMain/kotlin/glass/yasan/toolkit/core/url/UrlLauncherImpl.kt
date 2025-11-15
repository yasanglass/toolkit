package glass.yasan.toolkit.core.url

internal expect class UrlLauncherImpl : UrlLauncher {
    override fun launch(url: String): Boolean
}
