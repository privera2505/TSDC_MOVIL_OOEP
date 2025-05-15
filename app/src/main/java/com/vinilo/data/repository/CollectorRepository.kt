package com.vinilo.data.repository

import com.vinilo.data.remote.service.ApiClient
import com.vinilo.data.remote.service.CollectorService
import com.vinilo.domain.model.Collector
import javax.inject.Inject

class CollectorRepository @Inject constructor() {

    private val collectorService: CollectorService =
        ApiClient.retrofit.create(CollectorService::class.java)

    suspend fun getCollectors(): List<Collector> {
        return collectorService.getCollectors()
    }

    suspend fun getCollectorById(id:Int): Collector{
        return collectorService.getCollectorById(id)
    }

}