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

    @Transaction
    @Query("SELECT * FROM albums ORDER BY price LIMIT 25")
    suspend fun fetchAlbumsSortedByPrice() : List<AlbumWithImagesEntity>

    @Transaction
    @Query("SELECT * FROM albums ORDER BY albumName LIMIT 25")
    suspend fun fetchAlbumsSortedByTitle() : List<AlbumWithImagesEntity>
}