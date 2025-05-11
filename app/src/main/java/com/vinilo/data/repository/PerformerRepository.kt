package com.vinilo.data.repository

import com.vinilo.data.remote.service.ApiClient
import com.vinilo.data.remote.service.PerformerService
import com.vinilo.domain.model.Performer

class PerformerRepository {

    private val perfomerService: PerformerService =
        ApiClient.retrofit.create(PerformerService::class.java)

    suspend fun getPerformers(): List<Performer> {
        return perfomerService.getPerformers()
    }

    suspend fun getPerformerById(id: Int): Performer {
        return perfomerService.getPerformerById(id)
    }

}