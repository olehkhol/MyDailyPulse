package my.daily.pulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(items = makeItems())
        }
    }
}

private fun makeItems(): List<Pair<String, String>> {
    val platform = Platform().also {
        it.logSystemInfo()
    }

    return listOf(
        Pair("Operation System", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", platform.density.toString()),
    )
}

@Preview
@Composable
private fun AppPreview() {
    MaterialTheme {
        App(items = makeItems())
    }
}