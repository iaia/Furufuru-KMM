package dev.iaiabot.furufuru_kmm.data.github

import dev.iaiabot.furufuru_kmm.util.GithubSettings
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

internal object GithubApiClient {

    fun create(
        httpClientEngine: HttpClientEngine,
        githubSettings: GithubSettings
    ): HttpClient {
        return HttpClient(httpClientEngine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        encodeDefaults = false
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }
            install(HttpTimeout) {

            }
            defaultRequest {
                header("Authorization", "token ${githubSettings.githubApiToken}")
            }
        }
    }
}
