package my.daily.pulse.articles

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {

    suspend fun getArticles(): List<ArticleRaw> {
        val articlesDb = dataSource.getAllArticles()

        if (articlesDb.isEmpty()) {
            val fetchedArticles = service.fetchArticles()

            dataSource.insertArticles(fetchedArticles)

            return fetchedArticles
        }

        return articlesDb
    }
}