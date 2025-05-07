package com.vinilo.data.repository

import com.vinilo.data.remote.services.ApiClient
import com.vinilo.data.remote.services.CollectorService
import com.vinilo.domain.model.Collector

class CollectorRepository {

    private val collectorService: CollectorService =
        ApiClient.retrofit.create(CollectorService::class.java)

    suspend fun getCollectors(): List<Collector> {
        return collectorService.getCollectors()
    }

}