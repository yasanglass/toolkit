package glass.yasan.toolkit.core.url

public interface UrlLauncher {

    public fun launch(
        url: String,
    ): UrlLaunchResult

    public fun launch(
        url: String,
        onFailure: (UrlLaunchResult.Failure) -> Unit,
    ) {
        when (val result = launch(url)) {
            is UrlLaunchResult.Success -> Unit
            is UrlLaunchResult.Failure -> onFailure(result)
        }
    }

}
