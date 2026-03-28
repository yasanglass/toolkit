package glass.yasan.toolkit.sample

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import glass.yasan.toolkit.sample.di.sampleModule
import kotlinx.browser.document
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(sampleModule)
    }

    ComposeViewport(document.getElementById("ComposeTarget")!!) {
        SampleApp()
    }
}
