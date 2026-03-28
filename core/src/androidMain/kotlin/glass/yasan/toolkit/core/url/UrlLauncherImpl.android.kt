package glass.yasan.toolkit.core.url

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

public actual class UrlLauncherImpl(
    private val context: Context,
) : UrlLauncher {

    actual override fun launch(url: String): UrlLaunchResult = try {
        val uri = Uri.parse(url)

        try {
            val customTabsIntent = CustomTabsIntent.Builder().build().apply {
                intent.apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            }
            customTabsIntent.launchUrl(context, uri)
            UrlLaunchResult.Success
        } catch (_: Exception) {
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
            UrlLaunchResult.Success
        }
    } catch (e: Exception) {
        UrlLaunchResult.Failure.Error(e)
    }
}
