package com.vinilo.data.remote.service

import com.vinilo.data.remote.dto.AwardDto
import com.vinilo.data.remote.dto.AwardReponseDto
import com.vinilo.data.remote.dto.PerformanceRequestDto
import com.vinilo.data.remote.dto.PerformerWinnerDto
import com.vinilo.domain.model.PerformerPrizeRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AwardService {

    @POST("prizes")
    suspend fun createAward(
        @Body award: PerformerPrizeRequest
    ): AwardReponseDto

    @GET("prizes")
    suspend fun getPrizes(): List<AwardReponseDto>

    @GET("prizes/{id}")
    suspend fun getPrizeById(@Path("id") id: Int): AwardDto

    @GET("prizes/{awardId}/performers")
    suspend fun getAwardWinners(@Path("awardId") awardId: Int): List<PerformerWinnerDto>

    @POST("/prizes/{prizeId}/musicians/{artistId}")
    suspend fun addWinner(
        @Path("prizeId") prizeId: Int,
        @Path("artistId") artistId: Int,
        @Body request: PerformanceRequestDto
    )

}