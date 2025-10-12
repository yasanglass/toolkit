package glass.yasan.toolkit.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import glass.yasan.toolkit.sample.di.sampleModule
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(sampleModule)
    }
    
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Toolkit",
        ) {
            SampleApp()
        }
    }
}
