package my.daily.pulse.articles.application

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import my.daily.pulse.articles.data.ArticleRaw
import my.daily.pulse.articles.data.ArticlesRepository
import kotlin.math.abs

class ArticlesUseCase(private val repo: ArticlesRepository) {

    suspend fun getArticles(forceFetch: Boolean): List<Article> {
        val articlesRaw = repo.getArticles(forceFetch)

        return mapArticles(articlesRaw)
    }

    private fun mapArticles(articlesRaw: List<ArticleRaw>): List<Article> {
        return articlesRaw.map {
            Article(
                title = it.title,
                desc = it.desc ?: "Click to find out more",
                date = getDaysAgoString(it.date),
                imageUrl = it.imageUrl
                    ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall2",
            )
        }
    }

    private fun getDaysAgoString(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )
        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}
