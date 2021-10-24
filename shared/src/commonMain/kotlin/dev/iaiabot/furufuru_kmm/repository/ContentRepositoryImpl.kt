package dev.iaiabot.furufuru_kmm.repository

import dev.iaiabot.furufuru_kmm.data.entity.ContentImageUrls
import dev.iaiabot.furufuru_kmm.data.github.GithubService
import dev.iaiabot.furufuru_kmm.data.github.request.Content
import dev.iaiabot.furufuru_kmm.util.GithubSettings

internal class ContentRepositoryImpl(
    private val settings: GithubSettings,
    private val service: GithubService,
) : ContentRepository {

    override suspend fun post(content: Content, path: String): ContentImageUrls? {
        service.postContent(
            settings.githubRepositoryOwner,
            settings.githubRepository,
            content,
            path
        ).content.run {
            return ContentImageUrls(
                htmlUrl,
                downloadUrl
            )
        }
    }
}
