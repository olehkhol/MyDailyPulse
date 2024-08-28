package my.daily.pulse.di

import app.cash.sqldelight.db.SqlDriver
import my.daily.pulse.db.DailyPulseDatabase
import my.daily.pulse.db.DatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }
    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}