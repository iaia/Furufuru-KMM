package dev.iaiabot.furufuru_kmm.di

import android.content.Context
import android.content.SharedPreferences
import android.util.LruCache
import dev.iaiabot.furufuru_kmm.data.local.ScreenshotDataSource
import dev.iaiabot.furufuru_kmm.data.local.ScreenshotDataSourceImpl
import dev.iaiabot.furufuru_kmm.data.local.UserDataSource
import dev.iaiabot.furufuru_kmm.data.local.UserDataSourceImpl
import dev.iaiabot.furufuru_kmm.repository.*
import dev.iaiabot.furufuru_kmm.usecase.GetScreenShotUseCase
import dev.iaiabot.furufuru_kmm.usecase.GetScreenShotUseCaseImpl
import dev.iaiabot.furufuru_kmm.usecase.PostIssueUseCase
import dev.iaiabot.furufuru_kmm.usecase.PostIssueUseCaseImpl
import dev.iaiabot.furufuru_kmm.usecase.user.LoadUserNameUseCase
import dev.iaiabot.furufuru_kmm.usecase.user.LoadUserNameUseCaseImpl
import dev.iaiabot.furufuru_kmm.usecase.user.SaveUsernameUseCase
import dev.iaiabot.furufuru_kmm.usecase.user.SaveUsernameUseCaseImpl
import dev.iaiabot.furufuru_kmm.util.GithubSettings
import dev.iaiabot.furufuru_kmm.util.ScreenShotter
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal fun diModules() = listOf(
    apiModule,
    repositoryModule,
    useCaseModule,
    utilModule,
    dataModule,
    androidModule,
)

private val apiModule = module {
    single<dev.iaiabot.furufuru_kmm.data.github.GithubService> { dev.iaiabot.furufuru_kmm.data.github.GithubApiClient.build() }
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
    single<PostIssueUseCase> { PostIssueUseCaseImpl(get(), get(), get(), get(), get()) }
    single<GetScreenShotUseCase> { GetScreenShotUseCaseImpl(get()) }
}

private val utilModule = module {
    single { ScreenShotter(get()) }
    single { GithubSettings() }
    single(named("ScreenShotCache")) { LruCache<String, String>(1) }
}

private val dataModule = module {
    single<UserDataSource> { UserDataSourceImpl(get()) }
    single<ScreenshotDataSource> { ScreenshotDataSourceImpl(get(named("ScreenShotCache"))) }
}

private val androidModule = module {
    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            "furufuru",
            Context.MODE_PRIVATE
        )
    }
}
