package dev.iaiabot.furufuru_kmm.usecase.user

import dev.iaiabot.furufuru_kmm.repository.UserRepository

internal interface SaveUsernameUseCase {
    suspend operator fun invoke(username: String?)
}

internal class SaveUsernameUseCaseImpl(
    private val userRepository: UserRepository
) : SaveUsernameUseCase {

    override suspend fun invoke(username: String?) {
        if (!username.isNullOrBlank()) {
            userRepository.saveUserName(username)
        }
    }
}
