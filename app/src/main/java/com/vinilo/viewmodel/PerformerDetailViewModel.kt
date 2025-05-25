package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.data.remote.service.ApiClient
import com.vinilo.data.remote.service.AwardService
import com.vinilo.data.remote.service.PerformerService
import com.vinilo.data.repository.PerformerRepository
import com.vinilo.domain.model.Performer
import kotlinx.coroutines.launch

class PerformerDetailViewModel : ViewModel() {

    private val performerService = ApiClient.retrofit.create(PerformerService::class.java)
    private val awardService = ApiClient.retrofit.create(AwardService::class.java)
    private val repository = PerformerRepository(performerService, awardService)

    val performer = MutableLiveData<Performer>()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchPerformers(id: Int) {
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
