package my.daily.pulse.articles.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import my.daily.pulse.articles.application.ArticlesUseCase

class ArticlesViewModel(
    private val useCase: ArticlesUseCase
) : ViewModel() {

    private val _articlesState = MutableStateFlow(ArticlesState(loading = true))
    val articlesState = _articlesState.asStateFlow()

    init {
        getArticle()
    }

    fun refresh() {
        viewModelScope.launch {
            _articlesState.emit(_articlesState.value.copy(loading = true))

            val fetchedArticles = useCase.getArticles(forceFetch = true)

            _articlesState.emit(ArticlesState(articles = fetchedArticles))
        }
    }

    private fun getArticle(forceFetch: Boolean = false) {
        viewModelScope.launch {
            val fetchedArticles = useCase.getArticles(forceFetch)

            _articlesState.emit(ArticlesState(articles = fetchedArticles))
        }
    }
}
