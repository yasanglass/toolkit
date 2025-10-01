package glass.yasan.toolkit.about.domain.model

public data class Developer(
    val name: String = "Yasan Glass",
    val about: String = "Developer & Designer",
    val socials: Socials = Socials(),
) {

    public data class Socials(
        val bluesky: String = "https://bsky.app/profile/yasan.glass",
        val discordServer: String = "https://discord.gg/8BQrfyA",
        val email: String = "mailto:yasanglass@gmail.com",
        val github: String = "https://github.com/yasanglass",
        val gumroad: String = "https://yasanglass.gumroad.com",
        val mastodon: String = "https://mastodon.social/@yasanglass",
        val playStore: String = "https://play.google.com/store/apps/dev?id=5035207490031558874",
        val telegramChannel: String = "https://t.me/YASANupdates",
        val website: String = "https://yasan.glass",
    )

}
