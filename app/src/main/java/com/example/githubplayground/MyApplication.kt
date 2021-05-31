package com.example.githubplayground

import android.app.Application
import com.example.githubplayground.di.databaseModule
import com.example.githubplayground.di.networkModule
import com.example.githubplayground.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created on : 30/05/21 | 22.52
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //koin here
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    databaseModule
                )
            )
        }
    }
}