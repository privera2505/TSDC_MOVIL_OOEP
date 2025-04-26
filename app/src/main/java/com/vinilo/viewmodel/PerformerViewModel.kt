package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinilo.repository.PerformerRepository
import com.vinilo.model.Performer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class PerformerViewModel : ViewModel() {

    private val repository = PerformerRepository()

    private val _performers = MutableLiveData<List<Performer>>()
    val performers : LiveData<List<Performer>> = _performers

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchPerformers() {
        viewModelScope.launch {
            try {
                val response = repository.getPerformers()
                _performers.value = response
            } catch (e: Exception) {
                _error.value = "Error al obtener los artistas: ${e.message}"
            }
        }
    }

}