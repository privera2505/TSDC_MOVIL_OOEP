package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.model.Album
import com.vinilo.repository.AlbumRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlbumsViewModel : ViewModel() {

    private val repository = AlbumRepository()

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchAlbums(){
        viewModelScope.launch {
            try {
                val response = repository.getAlbums()
                _albums.value = response
            } catch (e: Exception) {
                _error.value = "Error al obtener álbumes: ${e.message}"
            }
        }
    }
}
