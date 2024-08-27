package my.daily.pulse

import android.app.Application
import my.daily.pulse.di.sharedKoinModules
import my.daily.pulse.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@DailyPulseApp)
            modules(sharedKoinModules + viewModelsModule)
        }
    }
}
