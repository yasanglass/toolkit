package glass.yasan.toolkit.sample

import androidx.compose.ui.window.Window
import glass.yasan.toolkit.sample.di.sampleModule
import org.koin.core.context.startKoin
import platform.AppKit.NSApp
import platform.AppKit.NSApplication

fun main() {
    NSApplication.sharedApplication()
    startKoin {
        modules(sampleModule)
    }
    Window(title = "Toolkit") {
        SampleApp()
    }
    NSApp?.run()
}
