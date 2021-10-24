package dev.iaiabot.furufuru_kmm.data.github.response

import kotlinx.serialization.Serializable

@Serializable
data class IssueResponse(
    val body: String? = null
)
