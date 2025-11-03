package glass.yasan.toolkit.about.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.toolkit.compose.about.Res
import glass.yasan.toolkit.compose.about.dev_logo_black
import glass.yasan.toolkit.compose.about.dev_logo_white
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
public fun ToolkitDeveloperBanner(
    darkContainer: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(
                resource = if (darkContainer) {
                    Res.drawable.dev_logo_white
                } else {
                    Res.drawable.dev_logo_black
                },
            ),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .requiredHeight(height = 48.dp),
        )
    }
}

@Preview
@Composable
private fun DeveloperBrandingFooterPreview() {
    MaterialTheme {
        Surface {
            ToolkitDeveloperBanner(
                darkContainer = false,
            )
        }
    }
}
