package com.weemusic.android.di.core

import com.weemusic.android.repository.MainRepository
import com.weemusic.android.repository.MainRepositoryImpl
import com.weemusic.android.repository.localdatasource.MainLocalDataSource
import com.weemusic.android.repository.remotedatasource.MainNetworkDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        mainNetworkDataSource: MainNetworkDataSource,
        mainLocalDataSource: MainLocalDataSource
    ) : MainRepository {
        return MainRepositoryImpl(mainLocalDataSource, mainNetworkDataSource)
    }
}