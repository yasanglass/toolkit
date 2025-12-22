package glass.yasan.toolkit.about.presentation.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import glass.yasan.kepko.component.ButtonText
import glass.yasan.kepko.component.Midground
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.toolkit.about.domain.model.Developer
import glass.yasan.toolkit.about.domain.repository.AboutRepository
import glass.yasan.toolkit.core.url.UrlLauncher
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
public fun ToolkitDeveloperContent(
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    val aboutRepository: AboutRepository = koinInject()
    val urlLauncher: UrlLauncher = koinInject()

    val developer: Developer by aboutRepository.developer.collectAsStateWithLifecycle(Developer())

    ToolkitDeveloperContent(
        isDarkTheme = isDarkTheme,
        developer = developer,
        onDeveloperLinkClick = { link -> urlLauncher.launch(link.url) },
        modifier = modifier,
    )
}

@Composable
private fun ToolkitDeveloperContent(
    isDarkTheme: Boolean,
    developer: Developer,
    onDeveloperLinkClick: (Developer.Link) -> Unit,
    modifier: Modifier = Modifier,
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
            ButtonText(
                text = link.name,
                containerColor = KepkoTheme.colors.foreground,
                onClick = { onDeveloperLinkClick(link) },
                leadingIcon = null,
                trailingIcon = painterResource(resource = link.icon),
                modifier = Modifier
                    .widthIn(max = 512.dp),
            )
        }
    }
}

@OptIn(ExperimentalKepkoApi::class)
@Preview
@Composable
private fun ToolkitDeveloperContentPreview() {
    KepkoTheme {
        Midground {
            ToolkitDeveloperContent(
                isDarkTheme = isSystemInDarkTheme(),
                developer = Developer(),
                onDeveloperLinkClick = {},
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
