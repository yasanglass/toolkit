package glass.yasan.toolkit.sample

import androidx.compose.ui.window.ComposeUIViewController
import glass.yasan.toolkit.sample.di.sampleModule
import org.koin.core.context.startKoin

@Suppress("Unused", "FunctionName")
fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin {
            modules(sampleModule)
        }
    }
) {
    SampleApp()
}
