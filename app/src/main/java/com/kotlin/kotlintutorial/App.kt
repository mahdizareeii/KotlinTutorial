package com.kotlin.kotlintutorial

import android.app.Application
import com.kotlin.kotlintutorial.di.module.appModule
import com.kotlin.kotlintutorial.di.module.repoModule
import com.kotlin.kotlintutorial.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }

}