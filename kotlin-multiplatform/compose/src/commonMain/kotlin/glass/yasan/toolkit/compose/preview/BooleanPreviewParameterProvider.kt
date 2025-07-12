package glass.yasan.toolkit.compose.preview

import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

public class BooleanPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(true, false)
}
