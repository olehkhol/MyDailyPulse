package my.daily.pulse.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    titleText: String,
    actionIcon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    onActionClick: () -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = titleText) },
        navigationIcon = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = contentDescription,
                )
            }
        },
        modifier = modifier,
    )
}