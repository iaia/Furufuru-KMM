package dev.iaiabot.furufuru_kmm.sample

import android.app.Application
import dev.iaiabot.furufuru_kmm.android.feature.Furufuru

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Furufuru.Builder(
            this,
        ).settingGithub(
            BuildConfig.GITHUB_API_TOKEN,
            "iaia",
            "Furufuru"
        ).setLabels(
            "bug", "documentation"
        ).build()
    }
}
