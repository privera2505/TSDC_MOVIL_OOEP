package com.vinilo.repository

import com.vinilo.network.ApiClient
import com.vinilo.network.PerformerService
import com.vinilo.model.Performer

class PerformerRepository {

    private val perfomerService: PerformerService =
        ApiClient.retrofit.create(PerformerService::class.java)

    suspend fun getPerformers(): List<Performer> {
        return perfomerService.getPerformers()
    }

}