package dev.iaiabot.furufuru_kmm.data.local

import android.util.LruCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal interface ScreenshotDataSource {
    val screenShotFlow: Flow<String?>

    suspend fun save(fileStr: String)
    fun remove()
}

internal expect class ScreenshotDataSourceImpl(
    cache: LruCache<String, String>
) : ScreenshotDataSource {

    override val screenShotFlow: MutableStateFlow<String?>
}
