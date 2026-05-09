package glass.yasan.toolkit.about.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import glass.yasan.kepko.component.Button
import glass.yasan.kepko.component.Midground
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.toolkit.about.domain.model.Developer
import glass.yasan.toolkit.about.domain.repository.AboutRepository
import glass.yasan.toolkit.compose.about.Res
import glass.yasan.toolkit.compose.about.developer_links_community
import glass.yasan.toolkit.compose.about.developer_links_contact
import glass.yasan.toolkit.compose.about.developer_links_creations
import glass.yasan.toolkit.compose.about.developer_links_social
import glass.yasan.toolkit.core.url.UrlLauncher
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

/**
 * @param onTrackDeveloperLinkClick optional callback that is called before the developer link is launched.
 * Meant to be used for analytics.
 */
@Composable
public fun ToolkitDeveloperContent(
    modifier: Modifier = Modifier,
    onTrackDeveloperLinkClick: (Developer.Link) -> Unit,
) {
    val aboutRepository: AboutRepository = koinInject()
    val urlLauncher: UrlLauncher = koinInject()
    val scope = rememberCoroutineScope()

    val developer: Developer by aboutRepository.developer.collectAsStateWithLifecycle(Developer())

    ToolkitDeveloperContent(
        developer = developer,
        onDeveloperLinkClick = { link ->
            onTrackDeveloperLinkClick(link)
            scope.launch { urlLauncher.launch(link.url) }
        },
        modifier = modifier,
    )
}

@Composable
private fun ToolkitDeveloperContent(
    developer: Developer,
    onDeveloperLinkClick: (Developer.Link) -> Unit,
    modifier: Modifier = Modifier,
) {
    val linkSections: ImmutableList<LinkSection> = remember(developer.links) {
        developer.links.toLinkSections()
    }

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
                modifier = Modifier
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
        linkSections.forEach { section ->
            LinkSection(
                title = resolveTitle(group = section.group),
                links = section.links,
                onDeveloperLinkClick = onDeveloperLinkClick,
            )
        }
    }
}

private data class LinkSection(
    val group: Developer.Link.Group,
    val links: ImmutableList<Developer.Link>,
)

private fun ImmutableList<Developer.Link>.toLinkSections(): ImmutableList<LinkSection> {
    val linksByGroup = groupBy { link -> link.group }

    return Developer.Link.Group.entries
        .mapNotNull { group ->
            val links = linksByGroup[group].orEmpty()
            if (links.isEmpty()) {
                null
            } else {
                LinkSection(
                    group = group,
                    links = links.toImmutableList(),
                )
            }
        }
        .toImmutableList()
}

@Composable
private fun resolveTitle(group: Developer.Link.Group): String? =
    when (group) {
        Developer.Link.Group.STANDALONE -> null
        Developer.Link.Group.CONTACT -> stringResource(resource = Res.string.developer_links_contact)
        Developer.Link.Group.COMMUNITY -> stringResource(resource = Res.string.developer_links_community)
        Developer.Link.Group.CREATIONS -> stringResource(resource = Res.string.developer_links_creations)
        Developer.Link.Group.SOCIAL -> stringResource(resource = Res.string.developer_links_social)
    }


@Composable
private fun LinkSection(
    title: String?,
    links: ImmutableList<Developer.Link>,
    onDeveloperLinkClick: (Developer.Link) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (links.isEmpty()) return

    Column(
        verticalArrangement = Arrangement.spacedBy(space = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        title?.let {
            Text(
                text = it.uppercase(),
                color = KepkoTheme.colors.contentSubtle,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
        }
        links.forEach { link ->
            Button(
                text = link.name,
                onClick = { onDeveloperLinkClick(link) },
                leadingIcon = painterResource(resource = link.icon),
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
                developer = Developer(),
                onDeveloperLinkClick = {},
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
