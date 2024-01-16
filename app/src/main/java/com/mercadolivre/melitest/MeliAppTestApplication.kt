package com.mercadolivre.melitest

import android.app.Application
import com.mercadolivre.melitest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MeliAppTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MeliAppTestApplication)
            modules(appModule)
        }
    }
}
