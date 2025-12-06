package glass.yasan.toolkit.about.presentation.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.concrete.component.TextSubtle
import glass.yasan.concrete.foundation.theme.ConcreteTheme
import glass.yasan.toolkit.compose.about.Res
import glass.yasan.toolkit.compose.about.ic_github
import glass.yasan.toolkit.compose.preview.BooleanPreviewParameterProvider
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Composable
public fun ToolkitAppBanner(
    appName: String,
    appIcon: Painter,
    appVersionName: String,
    showBuildDetails: Boolean,
    buildDetails: ImmutableList<Any>,
    modifier: Modifier = Modifier,
) {
    val buildDetailsString = remember {
        buildDetails
            .map { it.toString().uppercase() }
            .joinToString(separator = " ") { it }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Image(
                painter = appIcon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = ConcreteTheme.colors.contentSubtle),
                modifier = Modifier
                    .requiredHeight(height = 24.dp),
            )

            TextSubtle(
                text = "$appName $appVersionName",
                fontSize = 14.sp,
            )
        }

        AnimatedVisibility(
            visible = showBuildDetails,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            TextSubtle(
                text = buildDetailsString,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clip(shape = MaterialTheme.shapes.extraLarge)
                    .background(color = ConcreteTheme.colors.background)
                    .padding(
                        horizontal = 12.dp,
                    )
            )
        }
    }
}

@Preview
@Composable
private fun ToolkitAppBannerPreview(
    @PreviewParameter(BooleanPreviewParameterProvider::class) showBuildDetails: Boolean,
) {
    ConcreteTheme {
        Surface {
            ToolkitAppBanner(
                appName = "Toolkit",
                appIcon = painterResource(resource = Res.drawable.ic_github),
                appVersionName = "1.0.0",
                showBuildDetails = showBuildDetails,
                buildDetails = persistentListOf(100, "flavor"),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
