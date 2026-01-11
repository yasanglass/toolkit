package glass.yasan.toolkit.sample.theme

import androidx.compose.runtime.Composable
import glass.yasan.kepko.foundation.theme.AnimatedKepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle

@Composable
fun AppTheme(
    style: ThemeStyle = ThemeStyle.LIGHT,
    content: @Composable () -> Unit
) {
    AnimatedKepkoTheme(
        style = style,
        content = content,
    )
}
