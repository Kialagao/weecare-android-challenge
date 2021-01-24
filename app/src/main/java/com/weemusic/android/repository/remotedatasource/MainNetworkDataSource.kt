package com.weemusic.android.repository.remotedatasource

import com.weemusic.android.network.albumdto.AlbumsContainer
import retrofit2.Response

interface MainNetworkDataSource {
    suspend fun getTopAlbums() : Response<AlbumsContainer>
}