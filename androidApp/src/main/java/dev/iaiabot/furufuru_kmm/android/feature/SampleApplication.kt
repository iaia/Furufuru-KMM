package dev.iaiabot.furufuru_kmm.android.feature

import android.app.Application
import dev.iaiabot.furufuru_kmm.di.androidModule
import dev.iaiabot.furufuru_kmm.di.diModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SampleApplication)
            modules(diModules())
            modules(androidModule, androidAppModule)
        }
        Furufuru.Builder(this).build()
    }
}
