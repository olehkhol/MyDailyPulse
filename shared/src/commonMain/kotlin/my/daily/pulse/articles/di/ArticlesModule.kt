package my.daily.pulse.articles.di

import my.daily.pulse.articles.ArticlesDataSource
import my.daily.pulse.articles.ArticlesRepository
import my.daily.pulse.articles.ArticlesService
import my.daily.pulse.articles.ArticlesUseCase
import my.daily.pulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single<ArticlesDataSource> { ArticlesDataSource(get()) }
    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesRepository> { ArticlesRepository(get(), get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
}
