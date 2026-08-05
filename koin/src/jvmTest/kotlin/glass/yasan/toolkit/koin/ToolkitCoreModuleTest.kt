package glass.yasan.toolkit.koin

import glass.yasan.toolkit.core.app.ToolkitApp
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify
import org.koin.dsl.module

class ToolkitCoreModuleTest : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun verifyModule() {
        module {
            includes(toolkitModule)
            single {
                ToolkitApp(
                    id = "test-app",
                    utmSource = "test",
                )
            }
        }.verify()
    }

}
