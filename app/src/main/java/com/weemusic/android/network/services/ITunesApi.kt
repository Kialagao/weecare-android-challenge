package com.weemusic.android.network.services

import com.google.gson.JsonObject
import com.weemusic.android.network.albumdto.AlbumsContainer
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface iTunesApi {

    /*
    @GET("/us/rss/topalbums/limit={limit}/json")
    fun getTopAlbums(@Path("limit") limit: Int = 25): Single<JsonObject>*/

    @GET("/us/rss/topalbums/limit={limit}/json")
    suspend fun fetchTopAlbums(@Path("limit") limit: Int = 25): Response<AlbumsContainer>
}