package dev.iaiabot.furufuru_kmm.repository

import dev.iaiabot.furufuru_kmm.data.entity.ContentImageUrls
import dev.iaiabot.furufuru_kmm.data.github.GithubService
import dev.iaiabot.furufuru_kmm.data.github.request.Content
import dev.iaiabot.furufuru_kmm.util.GithubSettings
import kotlinx.serialization.ExperimentalSerializationApi

internal class ContentRepositoryImpl(
    private val settings: GithubSettings,
    private val service: GithubService,
) : ContentRepository {

    @ExperimentalSerializationApi
    override suspend fun post(content: Content, path: String): ContentImageUrls? {
        service.postContent(
            settings.githubRepositoryOwner,
            settings.githubRepository,
            content,
            path
        ).run {
            if (code() != 201) {
                throw Exception("${errorBody()?.string() ?: ""}; ${message()}")
            }
            val contentResult =
                body()?.content ?: throw Exception("${errorBody().toString()}; ${message()}")
            return ContentImageUrls(
                contentResult.htmlUrl,
                contentResult.downloadUrl
            )
        }
    }
}
