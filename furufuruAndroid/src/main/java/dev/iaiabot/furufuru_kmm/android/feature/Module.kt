package dev.iaiabot.furufuru_kmm.android.feature

import android.content.Context
import android.content.SharedPreferences
import dev.iaiabot.furufuru_kmm.android.feature.ui.issue.IssueViewModel
import dev.iaiabot.furufuru_kmm.android.feature.ui.issue.IssueViewModelImpl
import dev.iaiabot.furufuru_kmm.android.feature.utils.screenshot.ScreenShotter
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidAppModule = module {
    single { ScreenShotter(get()) }
    viewModel<IssueViewModel> {
        IssueViewModelImpl(get(), get(), get(), get())
    }

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            "furufuru",
            Context.MODE_PRIVATE
        )
    }
}
