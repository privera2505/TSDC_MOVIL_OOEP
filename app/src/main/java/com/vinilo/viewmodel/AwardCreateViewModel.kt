package com.vinilo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.data.repository.AwardRepository
import com.vinilo.domain.model.PerformerPrizeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AwardCreateViewModel @Inject constructor(
    private val repository: AwardRepository
) : ViewModel() {

    fun createAward(nombre: String, desc: String, organizacion: String) {
        viewModelScope.launch {
            try {
                val request = PerformerPrizeRequest(nombre, desc, organizacion)
                val response = repository.createAward(request)
                println(response)
            } catch (e: Exception) {
                println("Exception: ${e.message}")
            }
        }
    }
}
