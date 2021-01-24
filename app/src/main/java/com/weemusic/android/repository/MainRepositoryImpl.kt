package com.weemusic.android.repository

import com.weemusic.android.db.entities.*
import com.weemusic.android.domain.Album
import com.weemusic.android.network.albumdto.asAlbumAndImageEntities
import com.weemusic.android.repository.localdatasource.MainLocalDataSource
import com.weemusic.android.repository.remotedatasource.MainNetworkDataSource
import java.lang.Exception

class MainRepositoryImpl(private val mainLocalDataSource: MainLocalDataSource,
                         private val mainNetworkDataSource: MainNetworkDataSource) : MainRepository{

    override suspend fun getTopAlbums() : List<Album> {
        try {
            return getAlbumsFromDB()
        } catch (e : Exception) {
            throw e
        }
    }

    override suspend fun getSortedAlbums(orderBy : String): List<Album> {
        try {
            return mainLocalDataSource.getSortedAlbums(orderBy)
        } catch (e : Exception) {
            throw e
        }
    }

    override suspend fun updateTopAlbums(): List<Album> {
        try {
            val newAlbumAndImageEntities = getAlbumsFromAPI()
            mainLocalDataSource.deleteAllAlbums()

            val newAlbums = newAlbumAndImageEntities.map {
                it.first.asAlbum(it.second.asImageUrls())
            }

            val albumEntities = mutableListOf<AlbumEntity>()
            val albumImageEntities = mutableListOf<AlbumImageEntity>()

            collectEntities(newAlbumAndImageEntities, albumEntities, albumImageEntities)

            mainLocalDataSource.insertAllAlbumImages(albumImageEntities)
            mainLocalDataSource.insertAllAlbums(albumEntities)
            return newAlbums
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun getAlbumsFromAPI() : List<Pair<AlbumEntity, List<AlbumImageEntity>>> {
        var albumAndImageEntities = listOf<Pair<AlbumEntity, List<AlbumImageEntity>>>()

        try {
            val response = mainNetworkDataSource.getTopAlbums()
            val body = response.body()

            if (body != null) {
                albumAndImageEntities = body.asAlbumAndImageEntities()
            }
        } catch (e : Exception) {
            throw e
        }
        return albumAndImageEntities
    }

    /*
     * Checks if top albums are available in DB. If not, makes a network request then stores the result
     * to DB.
     */
    private suspend fun getAlbumsFromDB() : List<Album> {
        var albums = listOf<Album>()

        try {
            albums = mainLocalDataSource.getTopAlbums()
        } catch (e: Exception) {
            throw e
        }

        if (albums.isNotEmpty()) {
            return albums
        } else {
            val albumAndImageEntities = getAlbumsFromAPI()
            albums = albumAndImageEntities.map {
                it.first.asAlbum(it.second.asImageUrls())
            }

            val albumEntities = mutableListOf<AlbumEntity>()
            val albumImageEntities = mutableListOf<AlbumImageEntity>()

            collectEntities(albumAndImageEntities, albumEntities, albumImageEntities)

            mainLocalDataSource.insertAllAlbumImages(albumImageEntities)
            mainLocalDataSource.insertAllAlbums(albumEntities)
        }
        return albums
    }

    private fun collectEntities(albumAndImageEntities: List<Pair<AlbumEntity, List<AlbumImageEntity>>>,
                                albumEntities: MutableList<AlbumEntity>,
                                albumImageEntities: MutableList<AlbumImageEntity>) {
        albumAndImageEntities.forEach {
            val images = it.second
            val album = it.first
            albumEntities.add(album)
            albumImageEntities.addAll(images as Collection<AlbumImageEntity>)
        }
    }
}