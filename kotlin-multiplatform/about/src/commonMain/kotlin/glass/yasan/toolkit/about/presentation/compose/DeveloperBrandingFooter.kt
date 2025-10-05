package glass.yasan.toolkit.about.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import glass.yasan.toolkit.compose.about.Res
import glass.yasan.toolkit.compose.spacer.VerticalSpacer

@Composable
public fun DeveloperBrandingFooter(
    dark: Boolean,
    modifier: Modifier = Modifier,
    spacerTop: Dp = 64.dp,
    spacerBottom: Dp = 64.dp,
    logoHeight: Dp = 48.dp,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (spacerTop > 0.dp) {
            VerticalSpacer(height = spacerTop)
        }

        AsyncImage(
            // Coil does not support KMP compile-safe resources yet
            // See https://github.com/coil-kt/coil/issues/2812
            model = if (dark) {
                Res.getUri(path = "drawable/yasan_type_logo_dark.svg")
            } else {
                Res.getUri(path = "drawable/yasan_type_logo_light.svg")
            },
            contentDescription = null,
            modifier = Modifier
                .requiredHeight(logoHeight),
        )

        if (spacerBottom > 0.dp) {
            VerticalSpacer(height = spacerBottom)
        }
    }
}
