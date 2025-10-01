package glass.yasan.toolkit.about.domain.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

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
        ),
        Link(
            name = "Email",
            url = "mailto:yasanglass@gmail.com",
        ),
        Link(
            name = "GitHub",
            url = "https://github.com/yasanglass",
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
    )

}
