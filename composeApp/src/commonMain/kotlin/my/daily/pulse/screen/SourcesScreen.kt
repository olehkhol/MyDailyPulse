package my.daily.pulse.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.daily.pulse.composable.Toolbar
import my.daily.pulse.sources.application.Source
import my.daily.pulse.sources.presentation.SourcesState
import my.daily.pulse.sources.presentation.SourcesViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterialApi::class)
@Composable
fun SourcesScreen(
    onUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    sourcesViewModel: SourcesViewModel = koinViewModel<SourcesViewModel>()
) {
    val sourcesState: SourcesState by sourcesViewModel.sourcesState.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = sourcesState.loading,
        onRefresh = { },
    )

    Column(modifier = modifier) {
        Toolbar(
            titleText = "Sources",
            actionIcon = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Up Button",
            modifier = Modifier.fillMaxWidth(),
            onActionClick = onUpButtonClick,
        )

        Box(modifier = modifier.weight(1f).pullRefresh(pullRefreshState)) {
            with(sourcesState) {
                if (sources.isNotEmpty()) {
                    SourcesListView(sources)
                } else error?.let { ErrorMessage(it) }
            }
            PullRefreshIndicator(
                refreshing = sourcesState.loading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                scale = true
            )
        }
    }
}

@Composable
fun SourcesListView(
    sources: List<Source>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(sources) { source ->
            SourceItemView(source = source)
        }
    }
}

@Composable
fun SourceItemView(source: Source) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = source.name,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = source.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${source.country} - ${source.lang}",
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}
