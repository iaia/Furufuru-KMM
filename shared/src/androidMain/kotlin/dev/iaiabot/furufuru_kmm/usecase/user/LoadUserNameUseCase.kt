package dev.iaiabot.furufuru_kmm.usecase.user

import dev.iaiabot.furufuru_kmm.repository.UserRepository

interface LoadUserNameUseCase {
    operator fun invoke(): String
}

internal class LoadUserNameUseCaseImpl(
    private val userRepository: UserRepository
) : LoadUserNameUseCase {

    override fun invoke(): String = userRepository.getUserName()
}
