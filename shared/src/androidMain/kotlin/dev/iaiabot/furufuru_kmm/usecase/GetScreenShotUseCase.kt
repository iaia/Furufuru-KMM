package dev.iaiabot.furufuru_kmm.usecase

import dev.iaiabot.furufuru_kmm.repository.ScreenshotRepository
import kotlinx.coroutines.flow.Flow

interface GetScreenShotUseCase {
    operator fun invoke(): Flow<String?>
}

internal class GetScreenShotUseCaseImpl(
    private val screenshotRepository: ScreenshotRepository,
) : GetScreenShotUseCase {

    override fun invoke() = screenshotRepository.screenShotFlow
}
