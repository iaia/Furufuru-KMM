package dev.iaiabot.furufuru_kmm.repository

import kotlinx.coroutines.flow.Flow

interface ScreenshotRepository {
    val screenShotFlow: Flow<String?>

    suspend fun save(fileStr: String)

    // fun load(remove: Boolean = false): String?
    fun remove()
}
