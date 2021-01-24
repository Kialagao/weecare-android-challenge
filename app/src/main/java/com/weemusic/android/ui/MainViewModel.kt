package com.weemusic.android.ui

import androidx.lifecycle.*
import com.weemusic.android.domain.Album
import com.weemusic.android.domain.usecases.GetSortedAlbumsUseCase
import com.weemusic.android.domain.usecases.GetTopAlbumsUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
    private val getSortedAlbumsUseCase: GetSortedAlbumsUseCase
) : ViewModel() {

    // LiveData for fetching errors. One is for observing, and the other is for view model to modify.
    private var _onError = MutableLiveData(false)
    val onError: LiveData<Boolean>
        get() = _onError

    // Flag to display the error message. One is for observing, and the other is for view model to modify.
    private var _isErrorShown = MutableLiveData(false)
    val isErrorShown: LiveData<Boolean>
        get() = _isErrorShown

    // LiveData for sorted albums
    private var _sortedAlbums = MutableLiveData(listOf<Album>())
    val sortedAlbums: LiveData<List<Album>>
        get() = _sortedAlbums

    // LiveData for unsorted albums
    private var _unsortedAlbums = MutableLiveData(listOf<Album>())
    val unsortedAlbums: LiveData<List<Album>>
        get() = _unsortedAlbums


    init {
        getTopAlbums()
    }

    fun getTopAlbums() {
        viewModelScope.launch {
            try {
                val albums = getTopAlbumsUseCase.execute()
                _unsortedAlbums.postValue(albums)
            } catch (e : Exception) {
                e.printStackTrace()
                _onError.value = true
            }
        }
    }

    fun getSortedAlbums(orderBy : String) {
        viewModelScope.launch {
            try {
                val sortedAlbums = getSortedAlbumsUseCase.execute(orderBy)
                _sortedAlbums.postValue(sortedAlbums)
            } catch (e : Exception) {
                _onError.value = true
            }
        }
    }

    fun onErrorShown() {
        _isErrorShown.value = true
    }
}