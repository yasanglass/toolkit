package glass.yasan.toolkit.core.url

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import glass.yasan.toolkit.core.annotation.InternalToolkitApi
import glass.yasan.toolkit.core.app.ToolkitApp
import glass.yasan.toolkit.core.tracking.withUtmSourceParameter
import glass.yasan.toolkit.core.url.UrlLaunchResult.Failure.Error
import glass.yasan.toolkit.core.url.UrlLaunchResult.Failure.InvalidUrl
import glass.yasan.toolkit.core.url.UrlLaunchResult.Failure.Unsupported
import glass.yasan.toolkit.core.url.UrlLaunchResult.Success

public actual class UrlLauncherImpl(
    private val context: Context,
    private val toolkitApp: ToolkitApp,
) : UrlLauncher {

    @OptIn(InternalToolkitApi::class)
    actual override suspend fun launch(url: String): UrlLaunchResult = try {
        val trackedUrl = url.withUtmSourceParameter(toolkitApp)
            ?: return InvalidUrl
        val uri = Uri.parse(trackedUrl.toString())

        try {
            val customTabsIntent = CustomTabsIntent.Builder().build().apply {
                intent.apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            }
            customTabsIntent.launchUrl(context, uri)
            Success
        } catch (_: Exception) {
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            try {
                context.startActivity(intent)
                Success
            } catch (_: ActivityNotFoundException) {
                Unsupported
            } catch (e: Exception) {
                Error(e)
            }
        }
    } catch (e: Exception) {
        Error(e)
    }
}
