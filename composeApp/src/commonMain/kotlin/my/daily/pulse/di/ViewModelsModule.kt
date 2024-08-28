package my.daily.pulse.di

import my.daily.pulse.articles.presentation.ArticlesViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { ArticlesViewModel(get()) }
}