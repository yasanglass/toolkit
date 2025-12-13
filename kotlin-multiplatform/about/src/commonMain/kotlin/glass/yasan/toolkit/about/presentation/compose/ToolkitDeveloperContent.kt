package glass.yasan.toolkit.about.presentation.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.kepko.component.Icon
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.toolkit.about.domain.model.Developer
import glass.yasan.toolkit.about.domain.repository.AboutRepository
import glass.yasan.toolkit.compose.coroutines.collectAsStateWithLifecycleIfAvailable
import glass.yasan.toolkit.core.url.UrlLauncher
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
public fun ToolkitDeveloperContent(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
    linkButtonContainerColor: Color = KepkoTheme.colors.foreground,
    linkButtonContentColor: Color = KepkoTheme.colors.content,
    linkButtonBorder: BorderStroke? = null,
) {
    val aboutRepository: AboutRepository = koinInject()
    val urlLauncher: UrlLauncher = koinInject()

    val developer: Developer by aboutRepository.developer.collectAsStateWithLifecycleIfAvailable(
        Developer()
    )

    ToolkitDeveloperContent(
        isDarkTheme = isDarkTheme,
        linkButtonContainerColor = linkButtonContainerColor,
        linkButtonContentColor = linkButtonContentColor,
        developer = developer,
        onDeveloperLinkClick = { link -> urlLauncher.launch(link.url) },
        linkButtonBorder = linkButtonBorder,
        modifier = modifier,
    )
}

@Composable
private fun ToolkitDeveloperContent(
    isDarkTheme: Boolean,
    developer: Developer,
    linkButtonContainerColor: Color,
    linkButtonContentColor: Color,
    onDeveloperLinkClick: (Developer.Link) -> Unit,
    modifier: Modifier = Modifier,
    linkButtonBorder: BorderStroke? = null,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp),
        ) {
            ToolkitDeveloperLogoVertical(
                isDarkTheme = isDarkTheme,
                modifier = modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 16.dp,
                    )
                    .size(128.dp),
            )
            Text(
                text = developer.biography,
                fontSize = 16.sp,
            )
        }
        developer.links.forEach { link ->
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = linkButtonContainerColor,
                    contentColor = linkButtonContentColor,
                ),
                shape = MaterialTheme.shapes.extraLarge,
                border = linkButtonBorder,
                onClick = { onDeveloperLinkClick(link) },
                modifier = Modifier.widthIn(max = 512.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = link.name.uppercase(),
                        modifier = Modifier
                            .weight(1f),
                    )
                    Icon(
                        painter = painterResource(resource = link.icon),
                        contentDescription = link.name,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ToolkitDeveloperContentPreview() {
    KepkoTheme {
        Surface(
            color = KepkoTheme.colors.midground,
        ) {
            ToolkitDeveloperContent(
                isDarkTheme = isSystemInDarkTheme(),
                developer = Developer(),
                linkButtonContainerColor = KepkoTheme.colors.foreground,
                linkButtonContentColor = KepkoTheme.colors.content,
                onDeveloperLinkClick = {},
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
