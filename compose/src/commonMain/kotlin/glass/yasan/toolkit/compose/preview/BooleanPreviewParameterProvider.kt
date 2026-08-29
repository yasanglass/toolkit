package glass.yasan.toolkit.compose.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

/**
 * A [PreviewParameterProvider] that provides Boolean values: `true`, `false`.
 *
 * @see NullableBooleanPreviewParameterProvider
 */
public class BooleanPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(true, false)
}
