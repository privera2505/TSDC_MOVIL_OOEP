package com.vinilo.data.repository

import com.vinilo.data.remote.service.ApiClient
import com.vinilo.data.remote.service.AwardService
import com.vinilo.domain.model.PerformerPrizeRequest
import com.vinilo.domain.model.PerformerPrizeResponse

class AwardRepository {

    private val awardService: AwardService =
        ApiClient.retrofit.create(AwardService::class.java)

    suspend fun createAward(request: PerformerPrizeRequest): PerformerPrizeResponse{
        return awardService.createAward(request)
    }

    suspend fun getPrizes(): List<PerformerPrizeResponse> {
        return awardService.getPrizes()
    }

}
