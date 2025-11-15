package glass.yasan.toolkit.core.url

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import co.touchlab.kermit.Logger
import glass.yasan.toolkit.core.url.UrlLauncher.Companion.ERROR_MESSAGE

internal actual class UrlLauncherImpl(
    private val context: Context,
) : UrlLauncher {

    actual override fun launch(url: String): Boolean = try {
        val uri = Uri.parse(url)

        try {
            val customTabsIntent = CustomTabsIntent.Builder().build().apply {
                intent.apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            }
            customTabsIntent.launchUrl(context, uri)
            true
        } catch (_: Exception) {
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
            true
        }
    } catch (e: Exception) {
        Logger.e(e) { "$ERROR_MESSAGE: $url" }
        false
    }
}
