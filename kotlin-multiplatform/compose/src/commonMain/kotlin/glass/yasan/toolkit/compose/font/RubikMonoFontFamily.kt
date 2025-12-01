package glass.yasan.toolkit.compose.font

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import glass.yasan.toolkit.compose.Res
import glass.yasan.toolkit.compose.rubik_mono
import org.jetbrains.compose.resources.Font

@Composable
public fun rubikMonoFontFamily(): FontFamily = FontFamily(
    Font(Res.font.rubik_mono),
)
