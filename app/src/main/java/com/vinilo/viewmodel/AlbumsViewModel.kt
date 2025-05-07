package com.vinilo.viewmodel

import androidx.lifecycle.*
import com.vinilo.data.repository.AlbumRepository
import com.vinilo.domain.model.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val repository: AlbumRepository
) : ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun fetchAlbums() {
        viewModelScope.launch {
            _albums.value = repository.getAlbums()
        }
    }
}
