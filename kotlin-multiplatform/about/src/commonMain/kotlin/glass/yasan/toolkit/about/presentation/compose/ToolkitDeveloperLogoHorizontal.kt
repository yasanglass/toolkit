package glass.yasan.toolkit.about.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import glass.yasan.toolkit.compose.about.Res
import glass.yasan.toolkit.compose.about.dev_logo_black_horizontal
import glass.yasan.toolkit.compose.about.dev_logo_white_horizontal
import org.jetbrains.compose.resources.painterResource

@Composable
public fun ToolkitDeveloperLogoHorizontal(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(
            resource = if (isDarkTheme) {
                Res.drawable.dev_logo_white_horizontal
            } else {
                Res.drawable.dev_logo_black_horizontal
            },
        ),
        contentDescription = null,
        modifier = modifier,
    )
}
