package dev.iaiabot.furufuru_kmm.android.feature

import android.app.Application
import android.content.pm.PackageInfo
import dev.iaiabot.furufuru_kmm.android.feature.di.androidAppModule
import dev.iaiabot.furufuru_kmm.android.feature.utils.lifecycle.FurufuruLifecycleCallback
import dev.iaiabot.furufuru_kmm.di.androidModule
import dev.iaiabot.furufuru_kmm.di.diModules
import dev.iaiabot.furufuru_kmm.util.GithubSettings
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

class Furufuru private constructor(
    private val application: Application,
) {

    class Builder(
        private val application: Application
    ) {
        private var githubApiToken: String? = null
        private var githubReposOwner: String? = null
        private var githubRepository: String? = null
        private var furufuruBranch: String? = null
        private var labels: List<String> = emptyList()

        fun settingGithub(
            githubApiToken: String,
            githubReposOwner: String,
            githubRepository: String,
            furufuruBranch: String? = null
        ): Builder {
            this.githubApiToken = githubApiToken
            this.githubReposOwner = githubReposOwner
            this.githubRepository = githubRepository
            this.furufuruBranch = furufuruBranch
            return this
        }

        fun setLabels(vararg labels: String): Builder {
            this.labels = labels.map { it }
            return this
        }

        fun build(): Furufuru {
            // TODO: すでにあるinstance破棄して新しく作り直したい
            val instance = getInstance(application) ?: throw Exception()
            instance.githubSettings.init(
                githubApiToken = githubApiToken ?: "",
                githubReposOwner = githubReposOwner ?: "",
                githubRepository = githubRepository ?: "",
                furufuruBranch = furufuruBranch,
            )
            instance.githubSettings.addLabels(labels)
            instance.start()
            return instance
        }
    }

    companion object {
        private var instance: Furufuru? = null

        internal fun getAppVersionName() = getInstance()?.getApplicationVersion()

        internal fun getApplicationName() = getInstance()?.getApplicationName()

        internal fun takeScreenshot() {
            getInstance()?.takeScreenshot()
        }

        private fun getInstance(application: Application? = null): Furufuru? {
            if (instance == null) {
                application?.let {
                    instance = Furufuru(application)
                }
            }
            return instance
        }
    }

    private val githubSettings: GithubSettings by inject(GithubSettings::class.java)
    private val applicationLifecycleCallbacks: FurufuruLifecycleCallback =
        FurufuruLifecycleCallback()

    init {
        startKoin {
            androidLogger()
            androidContext(application)
            modules(diModules())
            modules(androidModule, androidAppModule)
        }
    }

    internal fun start() {
        application.registerActivityLifecycleCallbacks(applicationLifecycleCallbacks)
    }

    fun takeScreenshot() {
        applicationLifecycleCallbacks.takeScreenshot()
    }

    private fun getApplicationName(): String? {
        val applicationInfo = application.applicationInfo
        val stringId = applicationInfo.labelRes
        return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else application.getString(
            stringId
        )
    }

    private fun getApplicationVersion(): String {
        val pInfo: PackageInfo =
            application.packageManager.getPackageInfo(
                application.packageName, 0
            )
        return pInfo.versionName
    }
}
