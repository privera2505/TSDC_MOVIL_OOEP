package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.model.Album
import com.vinilo.model.Performer
import com.vinilo.repository.AlbumRepository
import com.vinilo.repository.PerformerRepository
import kotlinx.coroutines.launch

class PerformerDetailViewModel : ViewModel() {

    private val repository = PerformerRepository()

    val performer = MutableLiveData<Performer>()
    //val albums: LiveData<Album> = album

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchPerformers(id:Int) {
        viewModelScope.launch {
            try {
                //println("Valueid" + id)
                val response = repository.getPerformerById(id)
                performer.value = response
            } catch (e: Exception) {
                _error.value = "Error al obtener álbumes: ${e.message}"
            }
        }
    }
}
