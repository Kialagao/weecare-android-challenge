package com.weemusic.android.repository

import android.util.Log
import com.weemusic.android.db.entities.AlbumEntity
import com.weemusic.android.db.entities.AlbumImageEntity
import com.weemusic.android.db.entities.asAlbumListDomainModelFromNetWork
import com.weemusic.android.db.entities.asImageUrls
import com.weemusic.android.domain.Album
import com.weemusic.android.network.albumdto.asAlbumAndImageEntities
import com.weemusic.android.repository.localdatasource.MainLocalDataSource
import com.weemusic.android.repository.remotedatasource.MainNetworkDataSource
import org.threeten.bp.LocalDate
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

    /*
    override suspend fun updateArtists(): List<Artist>? {
        val newListOfArtists= getArtistsFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistsToDB(newListOfArtists)
        artistCacheDataSource.saveArtistsToCache(newListOfArtists)
        return newListOfArtists
    }*/

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
            albums = albumAndImageEntities.asAlbumListDomainModelFromNetWork()

            val albumEntities = mutableListOf<AlbumEntity>()
            val albumImageEntities = mutableListOf<AlbumImageEntity>()

            albumAndImageEntities.forEach {
                val images = it.second
                val album = it.first
                albumEntities.add(album)
                albumImageEntities.addAll(images as Collection<AlbumImageEntity>)
            }

            mainLocalDataSource.insertAllAlbumImages(albumImageEntities)
            mainLocalDataSource.insertAllAlbums(albumEntities)
        }
        return albums
    }
}