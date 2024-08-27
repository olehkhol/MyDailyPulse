package my.daily.pulse.di

import my.daily.pulse.articles.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.KoinContext
import org.koin.core.context.startKoin

fun initKoin() {

    startKoin {
        modules(sharedKoinModules)
    }

    class ArticlesInjector : KoinComponent {

        val articlesViewModel: ArticlesViewModel by inject()
    }
}