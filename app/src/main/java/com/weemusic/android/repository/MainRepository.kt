package com.weemusic.android.repository

import com.weemusic.android.domain.Album
import java.nio.ByteOrder

interface MainRepository {

    suspend fun getTopAlbums() : List<Album>

    suspend fun getSortedAlbums(orderBy : String) : List<Album>

    suspend fun updateTopAlbums() : List<Album>
}