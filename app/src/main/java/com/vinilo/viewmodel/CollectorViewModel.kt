package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.domain.model.Collector
import com.vinilo.data.repository.CollectorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectorViewModel @Inject constructor(
    private val repository: CollectorRepository
): ViewModel()  {

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