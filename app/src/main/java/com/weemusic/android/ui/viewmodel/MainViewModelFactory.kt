package com.weemusic.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.weemusic.android.domain.usecases.GetSortedAlbumsUseCase
import com.weemusic.android.domain.usecases.GetTopAlbumsUseCase
import com.weemusic.android.domain.usecases.UpdateTopAlbumsUseCase

class MainViewModelFactory(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
    private val getSortedAlbumsUseCase: GetSortedAlbumsUseCase,
    private val updateTopAlbumsUseCase: UpdateTopAlbumsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(getTopAlbumsUseCase, getSortedAlbumsUseCase, updateTopAlbumsUseCase) as T
    }
}