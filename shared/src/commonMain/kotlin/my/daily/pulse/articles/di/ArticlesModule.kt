package my.daily.pulse.articles.di

import my.daily.pulse.articles.ArticlesService
import my.daily.pulse.articles.ArticlesUseCase
import my.daily.pulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
}
