package glass.yasan.toolkit.about.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.Foreground
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
public fun ToolkitDeveloperBanner(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ToolkitDeveloperLogoHorizontal(
            isDarkTheme = isDarkTheme,
            modifier = Modifier
                .padding(16.dp)
                .requiredHeight(height = 48.dp),
        )
    }
}

@OptIn(ExperimentalKepkoApi::class)
@Preview
@Composable
private fun DeveloperBrandingFooterPreview() {
    MaterialTheme {
        Foreground {
            ToolkitDeveloperBanner(
                isDarkTheme = false,
            )
        }
    }
}
