package my.daily.pulse.di

import my.daily.pulse.articles.di.articlesModule

val sharedKoinModules = listOf(
    articlesModule,
    networkModule,
)
