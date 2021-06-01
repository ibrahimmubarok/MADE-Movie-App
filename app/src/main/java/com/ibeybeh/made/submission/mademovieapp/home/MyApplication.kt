package com.ibeybeh.made.submission.mademovieapp.home

import android.app.Application
import com.ibeybeh.made.submission.core.di.databaseModule
import com.ibeybeh.made.submission.core.di.networkModule
import com.ibeybeh.made.submission.core.di.repositoryModule
import com.ibeybeh.made.submission.mademovieapp.di.useCaseModule
import com.ibeybeh.made.submission.mademovieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}