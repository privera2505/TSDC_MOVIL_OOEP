package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinilo.data.repository.AwardRepository
import com.vinilo.domain.model.PerformerPrizeRequest
import kotlinx.coroutines.launch

class AwardCreateViewModel (
    private val repository: AwardRepository = AwardRepository()
): ViewModel() {

    private val nombrePrize = MutableLiveData<String>()
    private val descripcionPrize = MutableLiveData<String>()
    private val organizacionPrize = MutableLiveData<String>()

    val isFormularioValido: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        val validador = {
            val c1 = nombrePrize.value ?: ""
            val c2 = descripcionPrize.value ?: ""
            val c3 = organizacionPrize.value ?: ""
            this.value = c1.isNotEmpty() && c2.isNotEmpty() && c3.isNotEmpty()
        }

        addSource(nombrePrize) { validador() }
        addSource(descripcionPrize) { validador() }
        addSource(organizacionPrize) { validador() }

    }

    //Metodos para actualizar los campos desde la UI
    fun actualizarNombre(texto : String){
        nombrePrize.value = texto
    }

    fun actualizarDescripcion(texto : String){
        descripcionPrize.value = texto
    }

    fun actualizarOrganization(texto : String){
        organizacionPrize.value = texto
    }

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