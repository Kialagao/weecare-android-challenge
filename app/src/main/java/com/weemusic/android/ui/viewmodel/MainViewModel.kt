package com.weemusic.android.ui.viewmodel

import androidx.lifecycle.*
import com.weemusic.android.domain.Album
import com.weemusic.android.domain.usecases.GetSortedAlbumsUseCase
import com.weemusic.android.domain.usecases.GetTopAlbumsUseCase
import com.weemusic.android.domain.usecases.UpdateTopAlbumsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
    private val getSortedAlbumsUseCase: GetSortedAlbumsUseCase,
    private val updateTopAlbumsUseCase: UpdateTopAlbumsUseCase
) : ViewModel() {

    /* Create two LiveDatas for each data to be observed.
       One is for observing, the other is for view model to modify. */

    // LiveData for fetching errors.
    private var _onError = MutableLiveData(false)
    val onError: LiveData<Boolean>
        get() = _onError

    // LiveData for sorted albums
    private var _sortedAlbums = MutableLiveData(listOf<Album>())
    val sortedAlbums: LiveData<List<Album>>
        get() = _sortedAlbums

    // LiveData for unsorted albums
    private var _unsortedAlbums = MutableLiveData(listOf<Album>())
    val unsortedAlbums: LiveData<List<Album>>
        get() = _unsortedAlbums

    // LiveData for refreshing
    private var _isRefreshing = MutableLiveData(true)
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    init {
        getTopAlbums()
    }

    fun getTopAlbums() {
        _isRefreshing.value = true
        viewModelScope.launch {
            try {
                val albums = getTopAlbumsUseCase.execute()
                _unsortedAlbums.postValue(albums)
            } catch (e : Exception) {
                e.printStackTrace()
                _onError.value = true
            } finally {
                _isRefreshing.postValue(false)
            }
        }
    }

    fun getSortedAlbums(orderBy : String) {
        _isRefreshing.value = true
        viewModelScope.launch {
            try {
                withContext(Dispatchers.Default) {
                    val sortedAlbums = getSortedAlbumsUseCase.execute(orderBy)
                    _sortedAlbums.postValue(sortedAlbums)
                }
            } catch (e : Exception) {
                _onError.value = true
            } finally {
                _isRefreshing.postValue(false)
            }
        }
    }

    fun updateTopAlbums() {
        _isRefreshing.value = true
        viewModelScope.launch {
            try {
                val albums = updateTopAlbumsUseCase.execute()
                _unsortedAlbums.postValue(albums)
            } catch (e : Exception) {
                e.printStackTrace()
                _onError.value = true
            } finally {
                _isRefreshing.postValue(false)
            }
        }
    }
}