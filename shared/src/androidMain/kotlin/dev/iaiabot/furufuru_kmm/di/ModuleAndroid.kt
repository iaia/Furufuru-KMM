package dev.iaiabot.furufuru_kmm.di

import dev.iaiabot.furufuru_kmm.data.github.GithubApiClient
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import org.koin.dsl.module

val androidModule = module {
    single<HttpClientEngine> {
        Android.create {
            connectTimeout = 100_000
            socketTimeout = 100_000
            // proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("localhost", 8080))
        }
    }
    single<HttpClient> {
        GithubApiClient.create(get(), get())
    }
}
