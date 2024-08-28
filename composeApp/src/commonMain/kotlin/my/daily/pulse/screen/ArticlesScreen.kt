package my.daily.pulse.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import my.daily.pulse.articles.application.Article
import my.daily.pulse.articles.presentation.ArticlesState
import my.daily.pulse.articles.presentation.ArticlesViewModel
import my.daily.pulse.composable.Toolbar
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterialApi::class)
@Composable
fun ArticlesScreen(
    onAboutButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    articlesViewModel: ArticlesViewModel = koinViewModel<ArticlesViewModel>(),
) {
    val articlesState: ArticlesState by articlesViewModel.articlesState.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = articlesState.loading,
        onRefresh = { articlesViewModel.refresh() },
    )

    Column(modifier = modifier) {
        Toolbar(
            titleText = "Articles",
            actionIcon = Icons.Outlined.Info,
            contentDescription = "About Device Button",
            modifier = Modifier.fillMaxWidth(),
            onActionClick = onAboutButtonClick,
        )

        Box(modifier = modifier.weight(1f).pullRefresh(pullRefreshState)) {
            with(articlesState) {
                if (articles.isNotEmpty()) {
                    ArticlesListView(articles)
                } else error?.let { ErrorMessage(it) }
            }
            PullRefreshIndicator(
                refreshing = articlesState.loading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                scale = true
            )
        }
    }
}

@Composable
fun ArticlesListView(
    articles: List<Article>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(articles) { article ->
            ArticleItemView(article = article)
        }
    }
}

@Composable
fun ArticleItemView(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val request = ImageRequest.Builder(LocalPlatformContext.current)
            .data(article.imageUrl)
            .memoryCacheKey(article.imageUrl)
            .diskCacheKey(article.imageUrl)
            .build()
        AsyncImage(
            model = request,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}