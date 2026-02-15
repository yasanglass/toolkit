package glass.yasan.toolkit.core.annotation

@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This is an internal glass.yasan.toolkit API" +
            " that should not be used from outside of glass.yasan.toolkit.",
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
)
public annotation class InternalToolkitApi
