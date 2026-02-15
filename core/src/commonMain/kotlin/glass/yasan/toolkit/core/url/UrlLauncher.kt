package glass.yasan.toolkit.core.url

public interface UrlLauncher {

    public companion object {
        internal const val ERROR_MESSAGE = "Failed to launch url"
    }

    /**
     * Launches the given [url] in the best possible platform-specific way.
     *
     * @return if url was launched successfully.
     */
    public fun launch(url: String): Boolean

}
