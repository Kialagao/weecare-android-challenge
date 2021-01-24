package com.weemusic.android.di.core

import android.content.Context
import com.weemusic.android.di.main.MainSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



@Module(subcomponents = [MainSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext() : Context {
        return context.applicationContext
    }
}