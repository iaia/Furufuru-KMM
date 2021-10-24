package dev.iaiabot.furufuru_kmm.data.github

import dev.iaiabot.furufuru_kmm.util.GithubSettings
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import org.koin.java.KoinJavaComponent.inject

internal object GithubApiClient {

    private val GITHUB_SETTINGS: GithubSettings by inject(GithubSettings::class.java)

    fun create(httpClientEngine: HttpClientEngine): HttpClient {
        return HttpClient(httpClientEngine) {
            /*
            engine {
                // this: AndroidEngineConfig
                connectTimeout = 100_000
                socketTimeout = 100_000
                proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("localhost", 8080))
            }
             */
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
                header("Authorization", "token ${GITHUB_SETTINGS.githubApiToken}")
            }
        }
    }

    /*
    fun build(): GithubService {
        return buildRetrofit()
            .create(GithubService::class.java)
    }
     */

    /*
    private fun buildRetrofit(): Retrofit {
        val contentType = MediaType.parse("application/json")!!
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request()
            val buffer = okio.Buffer()
            request.body()?.writeTo(buffer)
            Log.d("Furufuru request body", buffer.readUtf8())

            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "token ${GITHUB_SETTINGS.githubApiToken}")
                .build()
            chain.proceed(newRequest)
        }.build()
        return Retrofit.Builder().run {
            client(client)
            addConverterFactory(
                Json {
                    encodeDefaults = false
                    ignoreUnknownKeys = true
                    isLenient = true
                }.asConverterFactory(contentType)
            )
            // TODO: KMM
            // baseUrl(BuildConfig.GITHUB_API_URL)
            build()
        }
    }

     */
}
