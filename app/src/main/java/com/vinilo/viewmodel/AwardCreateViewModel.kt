package com.vinilo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.data.repository.AwardRepository
import com.vinilo.domain.model.PerformerPrizeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AwardCreateViewModel @Inject constructor(
    private val repository: AwardRepository
) : ViewModel() {

    private val _createAwardResponse = MutableStateFlow<String?>(null)
    val createAwardResponse: StateFlow<String?> = _createAwardResponse

    fun createAward(nombre: String, desc: String, organizacion: String) {
        viewModelScope.launch {
            try {
                val request = PerformerPrizeRequest(nombre, desc, organizacion)
                val response = repository.createAward(request)
                _createAwardResponse.value = "Éxito: $response"
            } catch (e: Exception) {
                println("Exception: ${e.message}")
            }
        }
    }
}
