package com.weemusic.android.di.core

import android.content.Context
import androidx.room.Room
import com.weemusic.android.db.dao.AlbumDao
import com.weemusic.android.db.database.AlbumDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAlbumDatabase(context: Context) : AlbumDatabase {
        return Room
            .databaseBuilder(context, AlbumDatabase::class.java, "albums")
            .build()
    }

    @Provides
    @Singleton
    fun provideAlbumDao(albumDatabase: AlbumDatabase) : AlbumDao {
        return albumDatabase.albumDao()
    }
}