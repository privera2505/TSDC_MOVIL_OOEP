package com.vinilo.data.remote.service

import com.vinilo.domain.model.PerformerPrizeRequest
import com.vinilo.domain.model.PerformerPrizeResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AwardService {
    @POST("prizes")
    suspend fun createAward(
        @Body award: PerformerPrizeRequest
    ): PerformerPrizeResponse
}