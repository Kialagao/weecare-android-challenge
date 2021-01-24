package com.weemusic.android.domain

import android.util.Log
import com.weemusic.android.network.services.iTunesApi
import com.weemusic.android.repository.MainRepository
import javax.inject.Inject

class GetTopAlbumsUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend fun execute() : List<Album> = mainRepository.getTopAlbums()
}