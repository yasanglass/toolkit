package glass.yasan.toolkit.sample

import android.app.Application
import glass.yasan.toolkit.sample.di.sampleModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SampleApplication)
            modules(sampleModule)
        }
    }
}

