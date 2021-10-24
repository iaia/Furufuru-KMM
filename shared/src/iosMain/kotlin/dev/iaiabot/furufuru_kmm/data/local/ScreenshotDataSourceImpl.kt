package dev.iaiabot.furufuru_kmm.data.local

import android.util.LruCache
import kotlinx.coroutines.flow.MutableStateFlow

internal actual class ScreenshotDataSourceImpl actual constructor(
    private val cache: LruCache<String, String>
) : ScreenshotDataSource {

    actual override val screenShotFlow: MutableStateFlow<String?>
        get() = TODO("Not yet implemented")

    override suspend fun save(fileStr: String) {
        TODO("Not yet implemented")
    }

    override fun remove() {
        TODO("Not yet implemented")
    }
}
