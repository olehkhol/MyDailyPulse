package my.daily.pulse.articles

import my.daily.pulse.db.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {

    fun getAllArticles(): List<ArticleRaw> =
        database.dailyPulseDatabaseQueries
            .selectAllArticles(::mapToArticleRaw)
            .executeAsList()

    private fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        url: String?
    ): ArticleRaw = ArticleRaw(title, desc, date, url)
}