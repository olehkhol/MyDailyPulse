package my.daily.pulse.sources.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesService(private val httpClient: HttpClient) {

    private val apiKey = "28376d163d03437b84e2b29b8402e338"

    suspend fun fetchSources(): List<SourceRaw> {
        val response: SourcesResponse = httpClient
            .get("https://newsapi.org/v2/top-headlines/sources?apiKey=$apiKey")
            .body()

        return response.sources
    }
}