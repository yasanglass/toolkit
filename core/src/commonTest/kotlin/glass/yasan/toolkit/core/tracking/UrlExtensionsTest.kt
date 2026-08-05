package glass.yasan.toolkit.core.tracking

import glass.yasan.toolkit.core.annotation.InternalToolkitApi
import glass.yasan.toolkit.core.app.ToolkitApp
import io.ktor.http.Url
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@OptIn(InternalToolkitApi::class)
class UrlExtensionsTest {

    @Test
    fun `HTTP and HTTPS URLs contain app UTM source`() {
        val urls = listOf(
            "http://example.com/path",
            "https://example.com/path",
        )

        urls.forEach { url ->
            val trackedUrl = url.withUtmSourceParameter(toolkitApp)

            assertEquals("toolkit", trackedUrl?.parameters?.get("utm_source"))
        }
    }

    @Test
    fun `non-web URLs do not contain app UTM source`() {
        val urls = listOf(
            "mailto:developer@example.com?subject=Toolkit",
            "tel:+420123456789",
            "toolkit://developer/profile?id=42",
        )

        urls.forEach { url ->
            val untrackedUrl = url.withUtmSourceParameter(toolkitApp)

            assertNull(untrackedUrl?.parameters?.get("utm_source"))
        }
    }

    @Test
    fun `tracking preserves existing query parameters and fragment`() {
        val trackedUrl = "https://example.com/path?campaign=summer#details"
            .withUtmSourceParameter(toolkitApp)

        assertEquals("summer", trackedUrl?.parameters?.get("campaign"))
        assertEquals("toolkit", trackedUrl?.parameters?.get("utm_source"))
        assertEquals("details", trackedUrl?.fragment)
    }

    @Test
    fun `tracking replaces an existing UTM source`() {
        val trackedUrl = "https://example.com/?utm_source=old&utm_source=duplicate"
            .withUtmSourceParameter(toolkitApp)

        assertEquals(listOf("toolkit"), trackedUrl?.parameters?.getAll("utm_source"))
    }

    @Test
    fun `tracking safely encodes the app UTM source`() {
        val app = ToolkitApp(
            id = "test-app",
            utmSource = "Toolkit App/Android",
        )

        val trackedUrl = Url("https://example.com").withUtmSourceParameter(app)

        assertEquals("Toolkit App/Android", trackedUrl.parameters["utm_source"])
    }

    @Test
    fun `invalid URL cannot be tracked`() {
        assertNull("https://example.com:not-a-port".withUtmSourceParameter(toolkitApp))
    }

    private companion object {
        val toolkitApp = ToolkitApp(
            id = "test-app",
            utmSource = "toolkit",
        )
    }
}
