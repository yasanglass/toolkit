package glass.yasan.toolkit.compose.preview

import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

/**
 * A [PreviewParameterProvider] that provides nullable Boolean values: `true`, `false`, and `null`.
 *
 * @see BooleanPreviewParameterProvider
 */
public class NullableBooleanPreviewParameterProvider : PreviewParameterProvider<Boolean?> {
    override val values: Sequence<Boolean?> = sequenceOf(true, false, null)
}
