package dev.iaiabot.furufuru_kmm.repository

import dev.iaiabot.furufuru_kmm.data.local.ScreenshotDataSource

internal class ScreenshotRepositoryImpl(
    private val screenshotDataSource: ScreenshotDataSource,
) : ScreenshotRepository {
    override val screenShotFlow = screenshotDataSource.screenShotFlow

    override suspend fun save(fileStr: String) {
        screenshotDataSource.save(fileStr)
    }

    override fun remove() {
        screenshotDataSource.remove()
    }
}
