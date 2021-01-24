package com.weemusic.android.di.core

import com.weemusic.android.network.services.iTunesApi
import com.weemusic.android.repository.remotedatasource.MainNetworkDataSource
import com.weemusic.android.repository.remotedatasource.MainNetworkDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule() {

    @Provides
    @Singleton
    fun provideMainNetworkDataSource(albumService: iTunesApi) : MainNetworkDataSource {
        return MainNetworkDataSourceImpl(albumService)
    }
}