package com.weemusic.android.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weemusic.android.db.dao.AlbumDao
import com.weemusic.android.db.entities.AlbumEntity
import com.weemusic.android.db.entities.AlbumImageEntity

@Database(entities = [AlbumEntity::class, AlbumImageEntity::class], version = 2, exportSchema = false)
abstract class AlbumDatabase : RoomDatabase() {
    abstract fun albumDao() : AlbumDao
}