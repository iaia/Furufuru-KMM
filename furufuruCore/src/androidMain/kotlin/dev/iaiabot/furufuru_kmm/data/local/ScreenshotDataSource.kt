package dev.iaiabot.furufuru_kmm.data.local

import android.util.LruCache
import kotlinx.coroutines.flow.MutableStateFlow

internal actual class ScreenshotDataSourceImpl actual constructor(
    private val cache: LruCache<String, String>
) : ScreenshotDataSource {
    companion object {
        private const val SCREENSHOT_KEY = "screenshot"
    }

    actual override val screenShotFlow: MutableStateFlow<String?> = MutableStateFlow<String?>(null)

    override suspend fun save(fileStr: String) {
        synchronized(cache) {
            cache.put(ScreenshotDataSourceImpl.SCREENSHOT_KEY, fileStr)
            screenShotFlow.tryEmit(fileStr)
        }
    }

    override fun remove() {
        synchronized(cache) {
            cache.remove(ScreenshotDataSourceImpl.SCREENSHOT_KEY)
            screenShotFlow.tryEmit(null)
        }
    }

}
