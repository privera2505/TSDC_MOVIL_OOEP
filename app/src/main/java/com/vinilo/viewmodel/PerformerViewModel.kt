package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinilo.data.repository.PerformerRepository
import com.vinilo.domain.model.Performer
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PerformerViewModel @Inject constructor(
    private val repository: PerformerRepository
) : ViewModel() {

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

    fun fetchPerformersNotInPrize(prizeId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getPerformersNotInPrize(prizeId)
                _performers.value = response
            } catch (e: Exception) {
                _error.value = "Error al obtener artistas disponibles: ${e.message}"
            }
        }
    }


}