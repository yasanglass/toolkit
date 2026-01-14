package glass.yasan.toolkit.sample.theme

import androidx.compose.runtime.Composable
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle

@Composable
fun AppTheme(
    style: ThemeStyle = ThemeStyle.LIGHT,
    content: @Composable () -> Unit
) {
    KepkoTheme(
        style = style,
        content = content,
    )
}
