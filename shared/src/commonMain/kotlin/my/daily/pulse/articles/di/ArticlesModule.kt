package my.daily.pulse.articles.di

import my.daily.pulse.articles.data.ArticlesDataSource
import my.daily.pulse.articles.data.ArticlesRepository
import my.daily.pulse.articles.data.ArticlesService
import my.daily.pulse.articles.application.ArticlesUseCase
import my.daily.pulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single<ArticlesDataSource> { ArticlesDataSource(get()) }
    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesRepository> { ArticlesRepository(get(), get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
}
