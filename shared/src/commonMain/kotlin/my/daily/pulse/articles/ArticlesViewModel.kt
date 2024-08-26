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
    val articlesState = _articlesState.asStateFlow()

    init {
        getArticle()
    }

    private fun getArticle() {
        viewModelScope.launch {
            delay(2000)

            _articlesState.emit(ArticlesState(error = "Something went wrong"))

            delay(2000)

            val fetchedArticles = fetchArticles()

            _articlesState.emit(ArticlesState(articles = fetchedArticles))
        }
    }

    private suspend fun fetchArticles(): List<Article> =
        withContext(Dispatchers.IO) { mockArticles }
}
