package com.example.gymly

import android.app.Application
import com.example.gymly.di.databaseModule
import com.example.gymly.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GymlyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@GymlyApplication)
            modules(databaseModule, viewModelModule)
        }
    }
}