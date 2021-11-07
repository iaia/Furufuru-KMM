package dev.iaiabot.furufuru_kmm.di

import android.util.LruCache
import dev.iaiabot.furufuru_kmm.data.github.GithubService
import dev.iaiabot.furufuru_kmm.data.github.GithubServiceImp
import dev.iaiabot.furufuru_kmm.data.local.ScreenshotDataSource
import dev.iaiabot.furufuru_kmm.data.local.ScreenshotDataSourceImpl
import dev.iaiabot.furufuru_kmm.data.local.UserDataSource
import dev.iaiabot.furufuru_kmm.data.local.UserDataSourceImpl
import dev.iaiabot.furufuru_kmm.repository.*
import dev.iaiabot.furufuru_kmm.usecase.*
import dev.iaiabot.furufuru_kmm.usecase.user.LoadUserNameUseCase
import dev.iaiabot.furufuru_kmm.usecase.user.LoadUserNameUseCaseImpl
import dev.iaiabot.furufuru_kmm.usecase.user.SaveUsernameUseCase
import dev.iaiabot.furufuru_kmm.usecase.user.SaveUsernameUseCaseImpl
import dev.iaiabot.furufuru_kmm.util.GithubSettings
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun diModules() = listOf(
    apiModule,
    repositoryModule,
    useCaseModule,
    utilModule,
    dataModule,
)

private val apiModule = module {
    single<GithubService> {
        GithubServiceImp(get())
    }
}

private val repositoryModule = module {
    single<IssueRepository> {
        IssueRepositoryImpl(
            get(),
            get()
        )
    }
    single<ContentRepository> {
        ContentRepositoryImpl(
            get(),
            get()
        )
    }
    single<ScreenshotRepository> {
        ScreenshotRepositoryImpl(get())
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}

private val useCaseModule = module {
    single<SaveUsernameUseCase> { SaveUsernameUseCaseImpl(get()) }
    single<LoadUserNameUseCase> { LoadUserNameUseCaseImpl(get()) }
    single<PostIssueUseCase> { PostIssueUseCaseImpl(get(), get(), get(), get(), get(), get()) }
    single<GetScreenShotUseCase> { GetScreenShotUseCaseImpl(get()) }
    single<GenerateUploadDestinationPathUseCase> { GenerateUploadDestinationPathUseCase() }
}

private val utilModule = module {
    single { GithubSettings() }
    single(named("ScreenShotCache")) { LruCache<String, String>(1) }
}

private val dataModule = module {
    single<UserDataSource> { UserDataSourceImpl(get()) }
    single<ScreenshotDataSource> { ScreenshotDataSourceImpl(get(named("ScreenShotCache"))) }
}

