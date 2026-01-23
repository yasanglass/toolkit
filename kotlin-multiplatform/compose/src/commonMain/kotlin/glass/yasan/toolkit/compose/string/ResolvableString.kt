package glass.yasan.toolkit.compose.string

import androidx.compose.runtime.Composable
import glass.yasan.toolkit.core.annotation.ExperimentalToolkitApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@ExperimentalToolkitApi
public sealed interface ResolvableString {

    public data class Resource(
        val resource: StringResource,
    ) : ResolvableString

    public data class ResourceWithArgs(
        public val resource: StringResource,
        public val formatArgs: List<Any>,
    ) : ResolvableString

    public data class Literal(
        val string: String,
    ) : ResolvableString

    @Composable
    @ExperimentalToolkitApi
    public fun resolve(): String =
        when (this) {
            is Resource -> stringResource(resource)
            is ResourceWithArgs -> stringResource(resource, formatArgs)
            is Literal -> string
        }

}
