package com.weemusic.android.repository.localdatasource

import com.weemusic.android.db.dao.AlbumDao
import com.weemusic.android.db.entities.AlbumEntity
import com.weemusic.android.db.entities.AlbumImageEntity
import com.weemusic.android.db.entities.asAlbums
import com.weemusic.android.domain.Album

class MainLocalDataSourceImpl(private val albumDao: AlbumDao) : MainLocalDataSource {

    override suspend fun insertAllAlbums(albums: List<AlbumEntity>) {
        albumDao.insertAllAlbums(albums)
    }

    override suspend fun insertAllAlbumImages(images: List<AlbumImageEntity>) {
        albumDao.insertAllAlbumImages(images)
    }

    override suspend fun getTopAlbums() : List<Album> {
        return albumDao.fetchTopAlbums().asAlbums()
    }

    override suspend fun getSortedAlbums(orderBy: String): List<Album> {
        return if (orderBy == "price") {
            albumDao.fetchAlbumsSortedByPrice().asAlbums()
        } else {
            albumDao.fetchAlbumsSortedByTitle().asAlbums()
        }
    }

    override suspend fun deleteAllAlbums() {
        albumDao.deleteAllAlbums()
    }
}