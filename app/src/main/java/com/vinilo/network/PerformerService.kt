package com.vinilo.network

import com.vinilo.model.Performer
import retrofit2.http.GET

interface PerformerService {
    @GET("musicians")
    suspend fun getPerformers(): List<Performer>
}