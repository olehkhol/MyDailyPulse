package my.daily.pulse.sources.di

import my.daily.pulse.sources.application.SourcesUseCase
import my.daily.pulse.sources.data.SourcesDataSource
import my.daily.pulse.sources.data.SourcesRepository
import my.daily.pulse.sources.data.SourcesService
import my.daily.pulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module

val sourcesModule = module {

    single<SourcesDataSource> { SourcesDataSource(get()) }
    single<SourcesService> { SourcesService(get()) }
    single<SourcesRepository> { SourcesRepository(get(), get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
}