package glass.yasan.toolkit.core.annotation

@RequiresOptIn(
    level = WARNING,
    message = "This Toolkit API is experimental and is likely to change or to be removed in the future.",
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
)
public annotation class ExperimentalToolkitApi
