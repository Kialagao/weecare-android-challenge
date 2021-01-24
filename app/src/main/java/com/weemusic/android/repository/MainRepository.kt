package com.weemusic.android.repository

import com.weemusic.android.domain.Album

interface MainRepository {

    suspend fun getTopAlbums() : List<Album>
}