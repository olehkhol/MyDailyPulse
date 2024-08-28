package my.daily.pulse.sources.data

class SourcesRepository(
    private val dataSource: SourcesDataSource,
    private val service: SourcesService
) {

    suspend fun getSources(): List<SourceRaw> {
        val sourcesDb = dataSource.getAllSources()

        if (sourcesDb.isEmpty()) {
            return fetchSources()
        }

        return sourcesDb
    }

    private suspend fun fetchSources(): List<SourceRaw> {
        val fetchedSources = service.fetchSources()

        dataSource.insertSources(fetchedSources)

        return fetchedSources
    }
}
