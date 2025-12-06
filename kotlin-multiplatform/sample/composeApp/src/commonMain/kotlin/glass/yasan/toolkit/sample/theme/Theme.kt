package glass.yasan.toolkit.sample.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import glass.yasan.concrete.foundation.theme.ConcreteTheme

@Composable
fun AppTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    ConcreteTheme(
        primary = Color(color = 0xFF7F52FF),
        isDark = isDark,
        content = content,
    )
}
