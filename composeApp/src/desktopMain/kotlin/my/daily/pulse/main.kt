package my.daily.pulse

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import my.daily.pulse.di.sharedKoinModules
import my.daily.pulse.di.viewModelsModule
import org.koin.core.context.startKoin

fun main() {

    startKoin {
        modules(sharedKoinModules + viewModelsModule)
    }

    return application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "MyDailyPulse",
        ) {
            App()
        }
    }
}
