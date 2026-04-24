package glass.yasan.toolkit.core.url

/**
 * Result of a [PlatformUrlLauncher] launch attempt.
 */
public sealed interface UrlLaunchResult {

    public val isSuccess: Boolean get() = this is Success

    public data object Success : UrlLaunchResult

    public sealed interface Failure : UrlLaunchResult {
        public data object InvalidUrl : Failure

        public data object Unsupported : Failure

        public data class Error(
            val exception: Exception,
        ) : Failure
    }
}
