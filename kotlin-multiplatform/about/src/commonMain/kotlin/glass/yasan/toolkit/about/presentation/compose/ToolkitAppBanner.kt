package glass.yasan.toolkit.about.presentation.compose

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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.toolkit.compose.about.Res
import glass.yasan.toolkit.compose.about.ic_github
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
public fun ToolkitAppBanner(
    appName: String,
    appIcon: Painter,
    appVersionName: String,
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
                modifier = Modifier
                    .requiredHeight(height = 24.dp),
            )

            Text(
                text = "$appName $appVersionName",
                fontSize = 14.sp,
                color = KepkoTheme.colors.contentSubtle,
            )
        }

        Text(
            text = buildDetailsString,
            fontSize = 12.sp,
            color = KepkoTheme.colors.contentSubtle,
            modifier = Modifier
                .padding(top = 16.dp)
                .clip(shape = MaterialTheme.shapes.extraLarge)
                .background(color = KepkoTheme.colors.background)
                .padding(
                    horizontal = 12.dp,
                )
        )
    }
}

@Preview
@Composable
private fun ToolkitAppBannerPreview() {
    KepkoTheme {
        Surface {
            ToolkitAppBanner(
                appName = "Toolkit",
                appIcon = painterResource(resource = Res.drawable.ic_github),
                appVersionName = "1.0.0",
                buildDetails = persistentListOf(100, "flavor"),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
