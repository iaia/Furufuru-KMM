package dev.iaiabot.furufuru_kmm.util

import dev.iaiabot.furufuru_kmm.repository.ScreenshotRepository

actual class ScreenShotter actual constructor(
    private val screenshotRepository: ScreenshotRepository
) {
    actual fun takeScreenshot() {
    }
}
