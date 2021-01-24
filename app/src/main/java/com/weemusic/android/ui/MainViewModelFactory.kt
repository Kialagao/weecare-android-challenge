package com.weemusic.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weemusic.android.domain.usecases.GetSortedAlbumsUseCase
import com.weemusic.android.domain.usecases.GetTopAlbumsUseCase

class MainViewModelFactory(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
    private val getSortedAlbumsUseCase: GetSortedAlbumsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(getTopAlbumsUseCase, getSortedAlbumsUseCase) as T
    }
}