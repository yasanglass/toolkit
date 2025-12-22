package glass.yasan.toolkit.about.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.toolkit.compose.about.Res
import glass.yasan.toolkit.compose.about.dev_logo_horizontal
import org.jetbrains.compose.resources.painterResource

@Composable
public fun ToolkitDeveloperLogoHorizontal(
    modifier: Modifier = Modifier,
    color: Color = KepkoTheme.colors.content
) {
    Image(
        painter = painterResource(resource = Res.drawable.dev_logo_horizontal),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color),
        modifier = modifier,
    )
}
