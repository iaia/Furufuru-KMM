package dev.iaiabot.furufuru_kmm.repository

import dev.iaiabot.furufuru_kmm.data.github.GithubService
import dev.iaiabot.furufuru_kmm.data.github.request.Issue
import dev.iaiabot.furufuru_kmm.util.GithubSettings

internal class IssueRepositoryImpl(
    private val settings: GithubSettings,
    private val service: GithubService,
) : IssueRepository {

    override suspend fun post(issue: Issue) {
        service.postIssue(
            settings.githubRepositoryOwner,
            settings.githubRepository,
            issue,
        )
    }
}
