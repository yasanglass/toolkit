package glass.yasan.toolkit.sample.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import glass.yasan.concrete.foundation.color.isDynamicAccentSupported
import glass.yasan.concrete.foundation.theme.ConcreteTheme

@Composable
fun AppTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    isDynamicAccentAllowed: Boolean = isDynamicAccentSupported(),
    content: @Composable () -> Unit
) {
    ConcreteTheme(
        primary = Color(0xFF77dd77),
        secondary = Color(0xFFffb347),
        tertiary = Color(0xFFff6961),
        isDark = isDark,
        isDynamicAccentAllowed = isDynamicAccentAllowed,
        content = content,
    )
}
