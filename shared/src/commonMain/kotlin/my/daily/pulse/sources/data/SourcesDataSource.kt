package my.daily.pulse.sources.data

import my.daily.pulse.db.DailyPulseDatabase

class SourcesDataSource(private val database: DailyPulseDatabase) {

    fun getAllSources(): List<SourceRaw> =
        database.dailyPulseDatabaseQueries
            .selectAllSources(::mapToSourceRaw)
            .executeAsList()

    fun insertSources(sources: List<SourceRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            sources.forEach { sourceRaw ->
                insertSource(sourceRaw)
            }
        }
    }

    fun clearSources() =
        database.dailyPulseDatabaseQueries.removeAllSources()

    private fun insertSource(sourceRaw: SourceRaw) {
        database.dailyPulseDatabaseQueries.insertSource(
            sourceRaw.id,
            sourceRaw.name,
            sourceRaw.desc,
            sourceRaw.lang,
            sourceRaw.country,
        )
    }

    private fun mapToSourceRaw(
        id: String,
        name: String,
        desc: String,
        lang: String,
        country: String,
    ): SourceRaw = SourceRaw(id, name, desc, lang, country)
}
