package my.daily.pulse.di

import my.daily.pulse.articles.di.articlesModule
import my.daily.pulse.sources.di.sourcesModule

val sharedKoinModules = listOf(
    articlesModule,
    sourcesModule,
    networkModule,
)
