package com.weemusic.android.repository.remotedatasource

import com.weemusic.android.network.albumdto.AlbumsContainer
import com.weemusic.android.network.services.iTunesApi
import retrofit2.Response

class MainNetworkDataSourceImpl(private val albumService: iTunesApi) : MainNetworkDataSource {

    override suspend fun getTopAlbums(): Response<AlbumsContainer> {
        return albumService.fetchTopAlbums()
    }

}