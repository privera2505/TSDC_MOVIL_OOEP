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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class PerformerViewModel : ViewModel() {

    private val performerService = ApiClient.retrofit.create(PerformerService::class.java)
    private val awardService = ApiClient.retrofit.create(AwardService::class.java)
    private val repository = PerformerRepository(performerService, awardService)

    private val _performers = MutableLiveData<List<Performer>>()
    val performers: LiveData<List<Performer>> = _performers

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

    fun addWinnerToPrize(
        prizeId: Int,
        artistId: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                dateFormat.timeZone = TimeZone.getTimeZone("GMT-05:00")
                val formattedDate = dateFormat.format(Date())


                repository.addWinnerToPrize(prizeId, artistId, formattedDate)
                onSuccess()
            } catch (e: Exception) {
                onError("Error al asociar artista: ${e.message}")
            }
        }
    }

}
