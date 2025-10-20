package glass.yasan.toolkit.about.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import glass.yasan.toolkit.compose.about.Res
import glass.yasan.toolkit.compose.about.dev_logo_black
import glass.yasan.toolkit.compose.about.dev_logo_white
import glass.yasan.toolkit.compose.spacer.VerticalSpacer
import org.jetbrains.compose.resources.painterResource

@Composable
public fun DeveloperBrandingFooter(
    darkContainer: Boolean,
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
                .requiredHeight(logoHeight),
        )

        if (spacerBottom > 0.dp) {
            VerticalSpacer(height = spacerBottom)
        }
    }
}

@Composable
public fun DeveloperBrandingFooter(
    modifier: Modifier = Modifier,
    spacerTop: Dp = 64.dp,
    spacerBottom: Dp = 64.dp,
    logoHeight: Dp = 48.dp,
) {
    DeveloperBrandingFooter(
        darkContainer = isSystemInDarkTheme(),
        modifier = modifier,
        spacerTop = spacerTop,
        spacerBottom = spacerBottom,
        logoHeight = logoHeight,
    )
}
