package com.vinilo.data.remote.service

import com.vinilo.data.remote.dto.AwardDto
import com.vinilo.data.remote.dto.PerformerWinnerDto
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

    @GET("prizes/{awardId}/performers")
    suspend fun getAwardWinners(@Path("awardId") awardId: Int): List<PerformerWinnerDto>

}