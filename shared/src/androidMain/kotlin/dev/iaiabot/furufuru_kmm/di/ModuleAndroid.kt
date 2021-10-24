package dev.iaiabot.furufuru_kmm.di

import dev.iaiabot.furufuru_kmm.data.github.GithubApiClient
import io.ktor.client.*
import io.ktor.client.engine.android.*
import org.koin.dsl.module
import java.net.InetSocketAddress
import java.net.Proxy

val androidModule = module {
    single<HttpClient> {
        GithubApiClient.create(
            Android.create {
                connectTimeout = 100_000
                socketTimeout = 100_000
                proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("localhost", 8080))
            },
            get()
        )
    }
}
