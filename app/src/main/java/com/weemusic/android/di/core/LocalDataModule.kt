package com.weemusic.android.di.core

import com.weemusic.android.db.dao.AlbumDao
import com.weemusic.android.repository.localdatasource.MainLocalDataSource
import com.weemusic.android.repository.localdatasource.MainLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideMainLocalDataSource(albumDao: AlbumDao) : MainLocalDataSource {
        return MainLocalDataSourceImpl(albumDao)
    }
}