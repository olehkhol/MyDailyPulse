package my.daily.pulse.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import my.daily.pulse.Platform
import my.daily.pulse.composable.Toolbar
import androidx.compose.foundation.lazy.items

@Composable
fun AboutScreen(
    onUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Toolbar(
            titleText = "About Device",
            actionIcon = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Up Button",
            modifier = Modifier.fillMaxWidth(),
            onActionClick = onUpButtonClick,
        )
        ContentView(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun ContentView(modifier: Modifier = Modifier) {
    val items = makeItems()

    LazyColumn(modifier = modifier) {
        items(items) { row ->
            RowView(
                title = row.first,
                subtitle = row.second,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

private fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
    platform.logSystemInfo()

    return listOf(
        Pair("Operating System", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", platform.density.toString())
    )
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