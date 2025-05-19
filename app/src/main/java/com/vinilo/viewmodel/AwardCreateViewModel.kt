package com.vinilo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.data.repository.AwardRepository
import com.vinilo.domain.model.PerformerPrizeRequest
import kotlinx.coroutines.launch

class AwardCreateViewModel (
    private val repository: AwardRepository = AwardRepository()
): ViewModel() {

    fun createAward(nombre: String, desc: String, organizacion: String) {
        viewModelScope.launch {
            try {
                val request = PerformerPrizeRequest(nombre,desc,organizacion)
                val response = repository.createAward(request)
                println(response)
            } catch (e: Exception) {
                println("Exception: ${e.message}")
            }
        }
    }


}