package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.data.repository.AwardRepository
import com.vinilo.domain.model.Award
import com.vinilo.domain.model.PerformerWinner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AwardDetailViewModel @Inject constructor(
    private val repository: AwardRepository
) : ViewModel() {

    private val _award = MutableLiveData<Award>()
    val award: LiveData<Award> = _award

    private val _winners = MutableLiveData<List<PerformerWinner>>()
    val winners: LiveData<List<PerformerWinner>> = _winners

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadAward(awardId: Int) {
        viewModelScope.launch {
            try {
                _award.value = repository.getAwardById(awardId)
                _winners.value = repository.getAwardWinners(awardId)
            } catch (e: Exception) {
                _error.value = "Error al cargar el premio o ganadores: ${e.message}"
            }
        }
    }
}
