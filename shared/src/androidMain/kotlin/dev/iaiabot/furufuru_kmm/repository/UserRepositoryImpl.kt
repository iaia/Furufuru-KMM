package dev.iaiabot.furufuru_kmm.repository

import dev.iaiabot.furufuru_kmm.data.local.UserDataSource

internal class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {

    override fun getUserName(): String {
        return userDataSource.getUserName()
    }

    override fun saveUserName(userName: String) {
        userDataSource.saveUserName(userName)
    }
}
