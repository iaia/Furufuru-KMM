package dev.iaiabot.furufuru_kmm.repository

import dev.iaiabot.furufuru.util.GithubSettings
import dev.iaiabot.furufuru_kmm.data.github.GithubService
import dev.iaiabot.furufuru_kmm.data.github.request.Issue
import kotlinx.serialization.ExperimentalSerializationApi

internal class IssueRepositoryImpl(
    private val settings: GithubSettings,
    private val service: GithubService,
) : IssueRepository {

    @ExperimentalSerializationApi
    override suspend fun post(issue: Issue) {
        service.postIssue(
            settings.githubRepositoryOwner,
            settings.githubRepository,
            issue,
        )
    }
}
