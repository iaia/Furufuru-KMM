package dev.iaiabot.furufuru_kmm.repository

internal interface UserRepository {
    fun getUserName(): String
    fun saveUserName(userName: String)
}
