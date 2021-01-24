package com.weemusic.android.domain.usecases

import com.weemusic.android.domain.Album
import com.weemusic.android.repository.MainRepository
import javax.inject.Inject

class UpdateTopAlbumsUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend fun execute() : List<Album> = mainRepository.updateTopAlbums()
}