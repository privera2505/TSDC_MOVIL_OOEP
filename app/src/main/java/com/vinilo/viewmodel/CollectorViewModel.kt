package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.model.Collector
import com.vinilo.repository.CollectorRepository
import kotlinx.coroutines.launch

class CollectorViewModel: ViewModel()  {

    private val repository = CollectorRepository()

    private val _collectors = MutableLiveData<List<Collector>>()
    val collectors: LiveData<List<Collector>> = _collectors

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchCollectors() {
        viewModelScope.launch {
            try {
                val response = repository.getCollectors()
                _collectors.value = response
            } catch (e: Exception) {
                _error.value = "Error al obtener coleccionistas: ${e.message}"
            }
        }
    }

}