package com.vinilo.data.remote.service

import com.vinilo.domain.model.Collector
import retrofit2.http.GET

interface CollectorService {
    @GET("collectors")
    suspend fun getCollectors(): List<Collector>
}