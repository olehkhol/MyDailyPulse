package my.daily.pulse.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticlesViewModel : ViewModel() {

    private val _articlesState = MutableStateFlow(ArticlesState(loading = true))
    val articleState = _articlesState.asStateFlow()

    init {
        getArticle()
    }

    private fun getArticle() {
        viewModelScope.launch {
            val fetchedArticles = fetchArticles()

            delay(500)

            _articlesState.emit(ArticlesState(articles = fetchedArticles))
        }
    }

    private suspend fun fetchArticles(): List<Article> =
        withContext(Dispatchers.IO) { mockArticles }
}
