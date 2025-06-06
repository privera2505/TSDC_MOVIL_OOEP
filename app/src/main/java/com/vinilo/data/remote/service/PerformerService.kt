package com.vinilo.data.remote.service

import com.vinilo.domain.model.Performer
import retrofit2.http.GET
import retrofit2.http.Path

interface PerformerService {
    @GET("musicians")
    suspend fun getPerformers(): List<Performer>

    @GET("musicians/{id}")
    suspend fun getPerformerById(@Path("id") performerId: Int): Performer
}