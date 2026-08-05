package glass.yasan.toolkit.core.tracking

import glass.yasan.toolkit.core.annotation.InternalToolkitApi
import glass.yasan.toolkit.core.app.ToolkitApp
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.Url
import io.ktor.http.parseUrl

/**
 * Applies `utm_source` query parameter using the provided [ToolkitApp].
 */
@InternalToolkitApi
public fun String.withUtmSourceParameter(toolkitApp: ToolkitApp): Url? =
    parseUrl(this)?.let { url ->
        when (url.protocol) {
            URLProtocol.HTTP,
            URLProtocol.HTTPS,
            -> url.withUtmSourceParameter(toolkitApp)

            else -> url
        }
    }

@InternalToolkitApi
public fun Url.withUtmSourceParameter(
    toolkitApp: ToolkitApp,
): Url = withParameter(
    name = "utm_source",
    value = toolkitApp.utmSource,
)

@InternalToolkitApi
public fun Url.withParameter(name: String, value: String): Url =
    URLBuilder(this).apply {
        parameters[name] = value
    }.build()
