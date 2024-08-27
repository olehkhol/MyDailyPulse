package my.daily.pulse.articles

class ArticlesUseCase(private val service: ArticlesService) {

    suspend fun getArticles(): List<Article> {
        val articlesRaw = service.fetchArticles()

        return mapArticles(articlesRaw)
    }

    private fun mapArticles(articlesRaw: List<ArticleRaw>): List<Article> {
        return articlesRaw.map {
            Article(
                title = it.title,
                desc = it.desc ?: "Click to find out more",
                date = it.date,
                imageUrl = it.imageUrl ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall2",
            )
        }
    }
}
