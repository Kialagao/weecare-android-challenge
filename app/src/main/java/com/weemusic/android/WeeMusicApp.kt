package com.weemusic.android

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.weemusic.android.di.Injector
import com.weemusic.android.di.core.*
import com.weemusic.android.di.main.MainSubComponent

class WeeMusicApp : Application(), Injector {
    private lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this);
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule())
            .remoteDataModule(RemoteDataModule())
            .build()

    }

    override fun createMainSubComponent(): MainSubComponent {
        return appComponent.mainSubComponent().create()
    }
}