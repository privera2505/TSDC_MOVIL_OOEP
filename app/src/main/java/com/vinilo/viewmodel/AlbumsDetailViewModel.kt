package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.domain.model.Album
import com.vinilo.data.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsDetailViewModel @Inject constructor(
    private val repository: AlbumRepository
) : ViewModel() {

    private val album = MutableLiveData<Album>()
    val albums: LiveData<Album> = album

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchAlbums(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getAlbumById(id)
                album.value = response
            } catch (e: Exception) {
                _error.value = "Error al obtener álbumes: ${e.message}"
            }
        }
    }
}
