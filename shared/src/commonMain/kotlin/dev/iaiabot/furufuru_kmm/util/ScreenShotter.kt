package dev.iaiabot.furufuru_kmm.util

import dev.iaiabot.furufuru_kmm.repository.ScreenshotRepository

expect class ScreenShotter(screenshotRepository: ScreenshotRepository) {
    fun takeScreenshot()
}
