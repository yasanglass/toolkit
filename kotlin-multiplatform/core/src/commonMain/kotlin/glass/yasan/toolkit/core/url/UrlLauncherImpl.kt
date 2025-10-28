package glass.yasan.toolkit.core.url

public expect class UrlLauncherImpl : UrlLauncher {
    override fun launch(url: String): Boolean
}
