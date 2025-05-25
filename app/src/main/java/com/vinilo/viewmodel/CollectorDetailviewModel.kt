package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.data.repository.CollectorRepository
import com.vinilo.domain.model.Collector
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectorDetailviewModel @Inject constructor(
    private val repository: CollectorRepository
) : ViewModel() {

    private val collector = MutableLiveData<Collector>()
    val collectors: LiveData<Collector> = collector

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchCollector(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getCollectorById(id)
                collector.value = response
            } catch (e: Exception) {
                _error.value = "Error al obtener coleccionista: ${e.message}"
            }
        }
    }
}