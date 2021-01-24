package com.weemusic.android.di.core

import com.weemusic.android.di.main.MainSubComponent
import com.weemusic.android.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    DatabaseModule::class,
    DomainModule::class,
    RepositoryModule::class,
    RemoteDataModule::class,
    LocalDataModule::class])
interface AppComponent {

    fun mainSubComponent() : MainSubComponent.Factory
}