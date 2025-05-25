package com.vinilo.viewmodel

import androidx.lifecycle.*
import com.vinilo.data.repository.AwardRepository
import com.vinilo.domain.model.PerformerPrizeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AwardsViewModel @Inject constructor(
    private val repository: AwardRepository
) : ViewModel() {

    private val _awards = MutableLiveData<List<PerformerPrizeResponse>>()
    val awards: LiveData<List<PerformerPrizeResponse>> = _awards

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchAwards() {
        viewModelScope.launch {
            try {
                val response = repository.getPrizes()
                _awards.value = response
            } catch (e: Exception) {
                _error.value = "Error al obtener los premios: ${e.message}"
            }
        }
    }
}
