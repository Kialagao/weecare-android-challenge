package com.weemusic.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.weemusic.android.domain.GetTopAlbumsUseCase

class MainViewModel(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase
) : ViewModel() {

    fun getTopAlbums() = liveData {
        try {
            val albums = getTopAlbumsUseCase.execute()
            emit(albums)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}