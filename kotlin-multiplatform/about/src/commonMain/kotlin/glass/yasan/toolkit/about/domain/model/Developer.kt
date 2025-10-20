package glass.yasan.toolkit.about.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import glass.yasan.toolkit.compose.about.Res
import glass.yasan.toolkit.compose.about.ic_bluesky
import glass.yasan.toolkit.compose.about.ic_discord
import glass.yasan.toolkit.compose.about.ic_github
import glass.yasan.toolkit.compose.about.ic_language
import glass.yasan.toolkit.compose.about.ic_mail
import glass.yasan.toolkit.compose.about.ic_mastodon
import glass.yasan.toolkit.compose.about.ic_play_store
import glass.yasan.toolkit.compose.about.ic_storefront
import glass.yasan.toolkit.compose.about.ic_telegram
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.Icon as MaterialIcon

public data class Developer(
    val name: String = "Yasan Glass",
    val about: String = "Developer & Designer",
    val links: ImmutableList<Link> = persistentListOf(
        Link(
            name = "Bluesky",
            url = "https://bsky.app/profile/yasan.glass",
            icon = Res.drawable.ic_bluesky,
        ),
        Link(
            name = "Discord",
            url = "https://discord.gg/8BQrfyA",
            icon = Res.drawable.ic_discord,
        ),
        Link(
            name = "Email",
            url = "mailto:yasanglass@gmail.com",
            icon = Res.drawable.ic_mail,
        ),
        Link(
            name = "GitHub",
            url = "https://github.com/yasanglass",
            icon = Res.drawable.ic_github,
        ),
        Link(
            name = "Gumroad",
            url = "https://yasanglass.gumroad.com",
            icon = Res.drawable.ic_storefront,
        ),
        Link(
            name = "Mastodon",
            url = "https://mastodon.social/@yasanglass",
            icon = Res.drawable.ic_mastodon,
        ),
        Link(
            name = "Play Store",
            url = "https://play.google.com/store/apps/dev?id=5035207490031558874",
            icon = Res.drawable.ic_play_store,
        ),
        Link(
            name = "Telegram",
            url = "https://t.me/YASANupdates",
            icon = Res.drawable.ic_telegram,
        ),
        Link(
            name = "Website",
            url = "https://yasan.glass",
            icon = Res.drawable.ic_language,
        ),
    ),
) {

    public data class Link(
        val name: String,
        val url: String,
        private val icon: DrawableResource,
    ) {

        @Composable
        public fun Icon(modifier: Modifier = Modifier) {
            MaterialIcon(
                painter = painterResource(resource = icon),
                contentDescription = name,
                modifier = modifier,
            )
        }
    }

}
