package dev.iaiabot.furufuru_kmm.usecase

import dev.iaiabot.furufuru_kmm.repository.ScreenshotRepository

interface TakeScreenShotUseCase {
    suspend operator fun invoke()
}

internal class TakeScreenShotUseCaseImpl(
    private val screenshotRepository: ScreenshotRepository
) : TakeScreenShotUseCase {
    override suspend fun invoke() {
    }
}
