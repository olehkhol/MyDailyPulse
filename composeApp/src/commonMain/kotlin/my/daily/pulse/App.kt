package my.daily.pulse

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.network.ktor.KtorNetworkFetcherFactory
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import my.daily.pulse.articles.ArticlesViewModel
import my.daily.pulse.screen.ArticlesScreen
import okio.FileSystem

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App(disableDiskCache: Boolean = false) {
    MaterialTheme {
        setSingletonImageLoaderFactory { context ->
            if (disableDiskCache) context.asyncImageLoader() else
                context.asyncImageLoader().enableDiskCache()
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ArticlesScreen(
                onAboutButtonClick = {},
                articlesViewModel = ArticlesViewModel(),
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

/**
 * Create a new [ImageLoader] with the [PlatformContext].
 */
fun PlatformContext.asyncImageLoader() =
    ImageLoader
        .Builder(this)
        .components { add(KtorNetworkFetcherFactory()) }
        .crossfade(true)
        .networkCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(this, 0.25)
                .strongReferencesEnabled(true)
                .build()
        }
        .logger(DebugLogger())
        .build()

/**
 * Enable disk cache for the [ImageLoader].
 */
fun ImageLoader.enableDiskCache() = this.newBuilder()
    .diskCache {
        DiskCache.Builder()
            .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
            .build()
    }.build()
