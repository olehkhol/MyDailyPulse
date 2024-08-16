package my.daily.pulse.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen(
    onUpButtonClick: () -> Unit,
    items: List<Pair<String, String>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Toolbar(
            title = "About Device",
            modifier = Modifier.fillMaxWidth(),
        ) {
            // On Up
        }
        ContentView(
            items = items,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String,
    modifier: Modifier = Modifier,
    onUpButtonClick: () -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Up Button",
                )
            }
        },
        modifier = modifier,
    )
}

@Composable
fun ContentView(
    items: List<Pair<String, String>>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(items) { row: Pair<String, String> ->
            RowView(
                title = row.first,
                subtitle = row.second,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun RowView(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
    HorizontalDivider()
}