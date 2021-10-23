package dev.iaiabot.furufuru_kmm.repository

import dev.iaiabot.furufuru_kmm.data.entity.ContentImageUrls
import dev.iaiabot.furufuru_kmm.data.github.request.Content

internal interface ContentRepository {
    suspend fun post(content: Content, path: String): ContentImageUrls?
}
