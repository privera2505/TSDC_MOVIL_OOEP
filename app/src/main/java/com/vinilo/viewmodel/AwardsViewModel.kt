package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.data.repository.AwardRepository
import com.vinilo.domain.model.PerformerPrizeResponse
import kotlinx.coroutines.launch

class AwardsViewModel: ViewModel() {

    private val repository = AwardRepository()

    private val _awards = MutableLiveData<List<PerformerPrizeResponse>>()
    val awards: LiveData<List<PerformerPrizeResponse>> = _awards

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchAwards() {
        viewModelScope.launch {
            try {
                val response = repository.getPrizes()
                _awards.value = response
            }catch (e: Exception){
                _error.value = "Error al obtener los premios: ${e.message}"
            }
        }
    }

}