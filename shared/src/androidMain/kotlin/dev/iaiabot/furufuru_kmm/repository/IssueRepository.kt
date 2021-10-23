package dev.iaiabot.furufuru_kmm.repository

import dev.iaiabot.furufuru_kmm.data.github.request.Issue

internal interface IssueRepository {
    suspend fun post(issue: Issue)
}
