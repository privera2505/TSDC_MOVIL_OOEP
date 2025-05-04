package com.vinilo.network

import com.vinilo.model.Collector
import retrofit2.http.GET

interface CollectorService {
    @GET("collectors")
    suspend fun getCollectors(): List<Collector>
}