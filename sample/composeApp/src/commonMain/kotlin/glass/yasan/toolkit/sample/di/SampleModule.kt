package glass.yasan.toolkit.sample.di

import glass.yasan.toolkit.core.app.ToolkitApp
import glass.yasan.toolkit.koin.toolkitModule
import glass.yasan.toolkit.sample.SampleViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sampleModule = module {
    includes(toolkitModule)

    single {
        ToolkitApp(
            id = "toolkit-sample",
            utmSource = "glass.yasan.toolkit.sample",
        )
    }
    viewModelOf(::SampleViewModel)
}
