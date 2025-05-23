package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.domain.model.Performer
import com.vinilo.data.repository.PerformerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerformerDetailViewModel @Inject constructor(
    private val repository: PerformerRepository
) : ViewModel() {

    val performer = MutableLiveData<Performer>()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchPerformer(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getPerformerById(id)
                performer.value = response
            } catch (e: Exception) {
                _error.value = "Error al obtener artista: ${e.message}"
            }
        }
    }
}
