package com.vinilo.data.remote.service

import com.vinilo.domain.model.Collector
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectorService {
    @GET("collectors")
    suspend fun getCollectors(): List<Collector>

    @GET("collectors/{id}")
    suspend fun getCollectorById(@Path("id") collectorId: Int): Collector
}