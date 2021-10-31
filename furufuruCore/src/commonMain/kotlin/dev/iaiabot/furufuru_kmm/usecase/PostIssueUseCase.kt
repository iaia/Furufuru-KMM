package dev.iaiabot.furufuru_kmm.usecase

import dev.iaiabot.furufuru_kmm.data.entity.ContentImageUrls
import dev.iaiabot.furufuru_kmm.data.github.request.Content
import dev.iaiabot.furufuru_kmm.data.github.request.Issue
import dev.iaiabot.furufuru_kmm.repository.ContentRepository
import dev.iaiabot.furufuru_kmm.repository.IssueRepository
import dev.iaiabot.furufuru_kmm.repository.ScreenshotRepository
import dev.iaiabot.furufuru_kmm.usecase.user.SaveUsernameUseCase
import dev.iaiabot.furufuru_kmm.util.GithubSettings
import dev.iaiabot.furufuru_kmm.util.IssueBodyTemplate
import kotlinx.coroutines.flow.single

interface PostIssueUseCase {
    suspend operator fun invoke(
        title: String?,
        userName: String?,
        body: String?,
        labels: List<String> = emptyList(),
    )
}

internal class PostIssueUseCaseImpl(
    private val issueRepository: IssueRepository,
    private val screenshotRepository: ScreenshotRepository,
    private val contentRepository: ContentRepository,
    private val githubSettings: GithubSettings,
    private val saveUsernameUseCase: SaveUsernameUseCase,
    private val generateUploadDestinationPathUseCase: GenerateUploadDestinationPathUseCase,
) : PostIssueUseCase {

    override suspend fun invoke(
        title: String?,
        userName: String?,
        body: String?,
        labels: List<String>
    ) {
        if (title.isNullOrBlank()) {
            // TODO: no title exception作る
            throw Exception("no title")
        }

        saveUsernameUseCase(userName)

        val imageUrls = uploadScreenShot() ?: throw Exception("failed to upload screenshot")

        val issue = Issue(
            title,
            IssueBodyTemplate.createBody(
                userName ?: "",
                body ?: "",
                imageUrls.imageUrl,
                imageUrls.fileUrl
            ),
            labels = labels
        )

        issueRepository.post(issue)
    }

    private suspend fun uploadScreenShot(): ContentImageUrls? {
        val screenshot = screenshotRepository.screenShotFlow.single()
        if (screenshot.isNullOrEmpty()) {
            throw Exception("no screenshot")
        }
        val content = Content(
            content = screenshot,
            branch = githubSettings.furufuruBranch
        )
        try {
            val result = contentRepository.post(content, generateUploadDestinationPathUseCase())
            screenshotRepository.remove()
            return result
        } catch (e: Exception) {
            throw e
        }
    }
}
