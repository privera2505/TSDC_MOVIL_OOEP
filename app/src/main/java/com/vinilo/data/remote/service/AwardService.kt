package com.vinilo.data.remote.service

import com.vinilo.data.remote.dto.AwardDto
import com.vinilo.domain.model.PerformerPrizeRequest
import com.vinilo.domain.model.PerformerPrizeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AwardService {

    @POST("prizes")
    suspend fun createAward(
        @Body award: PerformerPrizeRequest
    ): PerformerPrizeResponse

    @GET("prizes")
    suspend fun getPrizes(): List<PerformerPrizeResponse>

    @GET("prizes/{id}")
    suspend fun getPrizeById(@Path("id") id: Int): AwardDto

    @POST("prizes/{id}/winner")
    suspend fun addWinner(
        @Path("id") awardId: Int,
        @Body body: Map<String, Int>
    )
}