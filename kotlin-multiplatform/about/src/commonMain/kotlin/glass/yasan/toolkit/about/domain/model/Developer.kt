package glass.yasan.toolkit.about.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import glass.yasan.toolkit.compose.about.Res
import glass.yasan.toolkit.compose.about.ic_email
import glass.yasan.toolkit.compose.about.ic_discord
import glass.yasan.toolkit.compose.about.ic_github
import glass.yasan.toolkit.compose.about.ic_link
import glass.yasan.toolkit.compose.about.ic_telegram
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

public data class Developer(
    val name: String = "Yasan Glass",
    val about: String = "Developer & Designer",
    val links: ImmutableList<Link> = persistentListOf(
        Link(
            name = "Bluesky",
            url = "https://bsky.app/profile/yasan.glass",
        ),
        Link(
            name = "Discord Server",
            url = "https://discord.gg/8BQrfyA",
            iconDrawableResource = Res.drawable.ic_discord
        ),
        Link(
            name = "Email",
            url = "mailto:yasanglass@gmail.com",
            iconDrawableResource = Res.drawable.ic_email
        ),
        Link(
            name = "GitHub",
            url = "https://github.com/yasanglass",
            iconDrawableResource = Res.drawable.ic_github
        ),
        Link(
            name = "Gumroad",
            url = "https://yasanglass.gumroad.com",
        ),
        Link(
            name = "Mastodon",
            url = "https://mastodon.social/@yasanglass",
        ),
        Link(
            name = "Play Store",
            url = "https://play.google.com/store/apps/dev?id=5035207490031558874",
        ),
        Link(
            name = "Telegram Channel",
            url = "https://t.me/YASANupdates",
            iconDrawableResource = Res.drawable.ic_telegram,
        ),
        Link(
            name = "Website",
            url = "https://yasan.glass",
        ),
    ),
) {

    public data class Link(
        val name: String,
        val url: String,
        private val iconDrawableResource: DrawableResource = Res.drawable.ic_link,
    ) {

        val iconPainter: @Composable (() -> Painter) = { painterResource(iconDrawableResource) }

    }

}
