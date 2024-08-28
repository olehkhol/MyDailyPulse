package my.daily.pulse.sources.application

import my.daily.pulse.sources.data.SourceRaw
import my.daily.pulse.sources.data.SourcesRepository

class SourcesUseCase(private val repo: SourcesRepository) {

    suspend fun getSources(): List<Source> {
        val sourcesRaw = repo.getSources()

        return mapSources(sourcesRaw)
    }

    private fun mapSources(sourcesRaw: List<SourceRaw>): List<Source> {
        return sourcesRaw.map {
            Source(
                id = it.id,
                name = it.name,
                desc = it.desc,
                lang = it.lang,
                country = it.country
            )
        }
    }
}