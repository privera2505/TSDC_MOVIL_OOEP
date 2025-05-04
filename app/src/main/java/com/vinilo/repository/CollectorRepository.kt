package com.vinilo.repository

import com.vinilo.network.ApiClient
import com.vinilo.network.CollectorService
import com.vinilo.model.Collector

class CollectorRepository {

    private val collectorService: CollectorService =
        ApiClient.retrofit.create(CollectorService::class.java)

    suspend fun getCollectors(): List<Collector> {
        return collectorService.getCollectors()
    }

}