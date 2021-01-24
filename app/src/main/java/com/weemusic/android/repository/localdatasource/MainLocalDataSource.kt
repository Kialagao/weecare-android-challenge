package com.weemusic.android.repository.localdatasource

import androidx.lifecycle.LiveData
import com.weemusic.android.db.entities.AlbumEntity
import com.weemusic.android.db.entities.AlbumImageEntity
import com.weemusic.android.domain.Album
import kotlinx.coroutines.flow.Flow

interface MainLocalDataSource {

    suspend fun insertAllAlbums(albums: List<AlbumEntity>)

    suspend fun insertAllAlbumImages(images: List<AlbumImageEntity>)

    suspend fun getTopAlbums() : List<Album>
}