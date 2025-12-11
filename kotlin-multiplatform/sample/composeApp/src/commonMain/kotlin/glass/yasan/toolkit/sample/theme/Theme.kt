package glass.yasan.toolkit.sample.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import glass.yasan.kepko.foundation.theme.KepkoTheme

@Composable
fun AppTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    KepkoTheme(
        isDark = isDark,
        content = content,
    )
}
