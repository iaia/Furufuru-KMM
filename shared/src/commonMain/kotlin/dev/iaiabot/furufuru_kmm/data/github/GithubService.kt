package dev.iaiabot.furufuru_kmm.data.github

import dev.iaiabot.furufuru_kmm.data.github.request.Content
import dev.iaiabot.furufuru_kmm.data.github.request.Issue
import dev.iaiabot.furufuru_kmm.data.github.response.ContentResponse
import dev.iaiabot.furufuru_kmm.data.github.response.IssueResponse
import io.ktor.client.*
import io.ktor.client.request.*

internal interface GithubService {

    suspend fun postIssue(
        owner: String,
        repo: String,
        issue: Issue
    ): IssueResponse

    suspend fun postContent(
        owner: String,
        repo: String,
        content: Content,
        path: String
    ): ContentResponse
}

internal class GithubServiceImp(
    private val client: HttpClient
) : GithubService {

    override suspend fun postIssue(owner: String, repo: String, issue: Issue): IssueResponse {
        return client.get<IssueResponse> {
            url {
                encodedPath = "/repos/${owner}/${repo}/issues"
                body = issue
            }
        }
    }

    override suspend fun postContent(
        owner: String,
        repo: String,
        content: Content,
        path: String
    ): ContentResponse {
        return client.get<ContentResponse> {
            url {
                encodedPath = "/repos/${owner}/${repo}/contents/${path}"
                body = content
            }
        }
        /*
        if (code() != 201) {
            throw Exception("${errorBody()?.string() ?: ""}; ${message()}")
        }
        val contentResult =
            body()?.content ?: throw Exception("${errorBody().toString()}; ${message()}")
         */
    }
}
