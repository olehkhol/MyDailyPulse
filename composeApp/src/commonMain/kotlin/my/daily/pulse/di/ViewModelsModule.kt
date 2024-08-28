package my.daily.pulse.di

import my.daily.pulse.articles.presentation.ArticlesViewModel
import my.daily.pulse.sources.presentation.SourcesViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { ArticlesViewModel(get()) }
    viewModel { SourcesViewModel(get()) }
}