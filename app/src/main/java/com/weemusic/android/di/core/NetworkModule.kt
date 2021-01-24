package com.weemusic.android.di.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.weemusic.android.network.services.iTunesApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun okHttpClient(
        loggingInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .apply { addInterceptor(loggingInterceptor) }.build()

    @Provides
    @Singleton
    fun iTunesApi(): iTunesApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .baseUrl("https://itunes.apple.com")
        .client(okHttpClient(httpLoggingInterceptor()))
        .build()
        .create(iTunesApi::class.java)
}