package glass.yasan.toolkit.core.koin

import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class ToolkitCoreModuleTest : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun verifyModule() {
        toolkitCoreModule.verify()
    }

}
