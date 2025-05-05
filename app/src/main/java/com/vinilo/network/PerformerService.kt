package com.vinilo.network

import com.vinilo.model.Album
import com.vinilo.model.Performer
import retrofit2.http.GET
import retrofit2.http.Path

interface PerformerService {
    @GET("musicians")
    suspend fun getPerformers(): List<Performer>

    @GET("musicians/{id}")
    suspend fun getPerformerById(@Path("id") performerId: Int): Performer
}