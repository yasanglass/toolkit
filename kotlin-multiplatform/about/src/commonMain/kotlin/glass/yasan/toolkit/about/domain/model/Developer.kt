package glass.yasan.toolkit.about.domain.model

import glass.yasan.toolkit.compose.about.Res
import glass.yasan.toolkit.compose.about.ic_bluesky
import glass.yasan.toolkit.compose.about.ic_discord
import glass.yasan.toolkit.compose.about.ic_github
import glass.yasan.toolkit.compose.about.ic_gumroad
import glass.yasan.toolkit.compose.about.ic_language
import glass.yasan.toolkit.compose.about.ic_mail
import glass.yasan.toolkit.compose.about.ic_mastodon
import glass.yasan.toolkit.compose.about.ic_play_store
import glass.yasan.toolkit.compose.about.ic_telegram
import io.ktor.http.parseUrl
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.DrawableResource

public data class Developer(
    val name: String = "Yasan Glass",
    val biography: String = "Developer & Designer",
    val picture: Picture = Picture(
        gravatar = Picture.Gravatar(
            id = "8489f66463e06f02d36e024188bcd7ae", // hello@yasan.glass
        ),
    ),
    val links: ImmutableList<Link> = persistentListOf(
        Link(
            name = "Bluesky",
            url = "https://bsky.app/profile/yasan.glass",
        ),
        Link(
            name = "Discord",
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
            name = "Telegram",
            url = "https://t.me/YASANupdates",
        ),
        Link(
            name = "Website",
            url = "https://yasan.glass",
        ),
    ),
) {

    public data class Picture(
        val gravatar: Gravatar,
    ) {

        /**
         * @property id MD5 hash of the gravatar profile email address
         */
        public data class Gravatar(
            private val id: String,
        ) {

            public fun getImageUrl(size: Int = 512): String = "$baseImageUrl?s=$size"

            private val baseImageUrl: String get() = "https://www.gravatar.com/avatar/$id"

        }

    }

    public data class Link(
        val name: String,
        val url: String,
    ) {

        public enum class Type {
            BLUESKY,
            DISCORD,
            EMAIL,
            GITHUB,
            GUMROAD,
            MASTODON,
            PLAY_STORE,
            TELEGRAM,
            WEBSITE,
        }

        public val type: Type
            get() {
                val parsedUrl = parseUrl(urlString = url)

                return when {
                    // Exact protocol
                    parsedUrl?.protocol?.name == "mailto" -> EMAIL
                    // Exact host
                    parsedUrl?.host == "bsky.app" -> BLUESKY
                    parsedUrl?.host == "discord.gg" -> DISCORD
                    parsedUrl?.host == "github.com" -> GITHUB
                    parsedUrl?.host == "play.google.com" -> PLAY_STORE
                    parsedUrl?.host == "t.me" -> TELEGRAM
                    // Other
                    name.contains("mastodon", true) -> MASTODON
                    parsedUrl?.host?.contains("gumroad.com") == true -> GUMROAD
                    else -> WEBSITE
                }
            }

        public val icon: DrawableResource
            get() = when (type) {
                Type.EMAIL -> Res.drawable.ic_mail
                Type.BLUESKY -> Res.drawable.ic_bluesky
                Type.DISCORD -> Res.drawable.ic_discord
                Type.GITHUB -> Res.drawable.ic_github
                Type.GUMROAD -> Res.drawable.ic_gumroad
                Type.MASTODON -> Res.drawable.ic_mastodon
                Type.PLAY_STORE -> Res.drawable.ic_play_store
                Type.TELEGRAM -> Res.drawable.ic_telegram
                Type.WEBSITE -> Res.drawable.ic_language
            }

    }

}
