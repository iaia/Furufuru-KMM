package dev.iaiabot.furufuru_kmm.data.github.response

import kotlinx.serialization.Serializable

@Serializable
internal data class ContentResponse(
    val content: ContentInfoResponse
)
