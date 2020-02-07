package com.github.vmas.beermeter.arch.app

import android.app.Application
import com.github.vmas.beermeter.arch.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerApp : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BeerApp)
            modules(appModule)
        }
    }
}
