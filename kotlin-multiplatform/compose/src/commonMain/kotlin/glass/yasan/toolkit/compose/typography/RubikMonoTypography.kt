package glass.yasan.toolkit.compose.typography

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import glass.yasan.toolkit.compose.font.rubikFontFamily
import glass.yasan.toolkit.compose.font.rubikMonoFontFamily

@Composable
public fun RubikMonoTypography(): Typography = ToolkitTypography(rubikMonoFontFamily())
