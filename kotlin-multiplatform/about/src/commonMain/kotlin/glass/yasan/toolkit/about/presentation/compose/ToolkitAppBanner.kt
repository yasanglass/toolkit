package glass.yasan.toolkit.about.presentation.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
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
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .animateContentSize()
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Image(
                painter = appIcon,
                contentDescription = null,
                modifier = Modifier
                    .requiredHeight(height = 24.dp),
            )

            Text(
                text = "$appName $appVersionName",
                style = MaterialTheme.typography.labelLarge,
            )
        }

        AnimatedVisibility(
            visible = showBuildDetails,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            Text(
                text = buildDetailsString,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(color = MaterialTheme.colorScheme.surfaceVariant)
                    .padding(
                        vertical = 4.dp,
                        horizontal = 8.dp,
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
    MaterialTheme {
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
