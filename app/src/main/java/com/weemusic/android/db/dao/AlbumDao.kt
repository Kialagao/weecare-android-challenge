package com.weemusic.android.db.dao

import androidx.room.*

import com.weemusic.android.db.entities.AlbumEntity
import com.weemusic.android.db.entities.AlbumImageEntity
import com.weemusic.android.db.entities.AlbumWithImagesEntity

@Dao
interface AlbumDao {

    @Transaction
    @Query("SELECT * FROM albums LIMIT 25")
    suspend fun fetchTopAlbums() : List<AlbumWithImagesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAlbums(albums : List<AlbumEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAlbumImages(images : List<AlbumImageEntity>)
    /*
    @Query()
    suspend fun fetchSortedAlbums() : List<Album>*/
}