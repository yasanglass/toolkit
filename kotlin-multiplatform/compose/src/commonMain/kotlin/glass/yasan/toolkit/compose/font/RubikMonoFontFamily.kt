package glass.yasan.toolkit.compose.font

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import glass.yasan.toolkit.compose.Res
import glass.yasan.toolkit.compose.rubik_black
import org.jetbrains.compose.resources.Font

@Composable
public fun rubikMonoFontFamily(): FontFamily = FontFamily(
    Font(Res.font.rubik_black, FontWeight.Black, FontStyle.Normal),
)
