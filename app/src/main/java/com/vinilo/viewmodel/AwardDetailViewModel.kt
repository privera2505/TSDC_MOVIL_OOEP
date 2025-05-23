package com.vinilo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.data.repository.AwardRepository
import com.vinilo.domain.model.Award
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AwardDetailViewModel @Inject constructor(
    private val repository: AwardRepository
) : ViewModel() {

    private val _award = MutableLiveData<Award>()
    val award: LiveData<Award> = _award

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadAward(awardId: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getAwardById(awardId)
                _award.value = result
            } catch (e: Exception) {
                _error.value = "Error al cargar detalle del premio: ${e.message}"
                Log.e("AwardDetailViewModel", "Error cargando premio", e)
            }
        }
    }
}
