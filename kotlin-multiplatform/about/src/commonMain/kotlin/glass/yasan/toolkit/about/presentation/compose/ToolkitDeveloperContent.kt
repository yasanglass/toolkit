package glass.yasan.toolkit.about.presentation.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import glass.yasan.toolkit.about.domain.model.Developer
import glass.yasan.toolkit.about.domain.repository.AboutRepository
import glass.yasan.toolkit.compose.coroutines.collectAsStateWithLifecycleIfAvailable
import glass.yasan.toolkit.core.url.UrlLauncher
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
public fun ToolkitDeveloperContent(
    modifier: Modifier = Modifier,
    linkButtonContainerColor: Color = MaterialTheme.colorScheme.surface,
    linkButtonContentColor: Color = MaterialTheme.colorScheme.onSurface,
    linkButtonBorder: BorderStroke? = null,
) {
    val aboutRepository: AboutRepository = koinInject()
    val urlLauncher: UrlLauncher = koinInject()

    val developer: Developer by aboutRepository.developer.collectAsStateWithLifecycleIfAvailable(Developer())

    ToolkitDeveloperContent(
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
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = developer.name,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = developer.biography,
                style = MaterialTheme.typography.bodyMedium,
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
    MaterialTheme {
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainer,
        ) {
            ToolkitDeveloperContent(
                developer = Developer(),
                linkButtonContainerColor = MaterialTheme.colorScheme.surface,
                linkButtonContentColor = MaterialTheme.colorScheme.onSurface,
                onDeveloperLinkClick = {},
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
