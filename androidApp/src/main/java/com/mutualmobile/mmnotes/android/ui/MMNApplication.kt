package com.mutualmobile.mmnotes.android.ui

import android.app.Application
import com.mutualmobile.mmnotes.data.di.commonModule
import com.mutualmobile.mmnotes.data.sources.local.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MMNApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MMNApplication)
            modules(
                listOf(
                    commonModule,
                    module {
                        single { DatabaseDriverFactory(this@MMNApplication).createDriver() }
                    }
                )
            )
        }
    }
}
