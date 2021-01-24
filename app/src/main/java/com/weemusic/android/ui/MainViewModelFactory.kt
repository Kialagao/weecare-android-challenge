package com.weemusic.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weemusic.android.domain.GetTopAlbumsUseCase

class MainViewModelFactory(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(getTopAlbumsUseCase) as T
    }
}