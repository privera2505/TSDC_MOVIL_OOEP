package com.vinilo.data.repository

import com.vinilo.data.remote.service.AwardService
import com.vinilo.domain.model.Award
import com.vinilo.domain.model.PerformerPrizeRequest
import com.vinilo.domain.model.PerformerPrizeResponse
import com.vinilo.utils.toDomain
import javax.inject.Inject

class AwardRepository @Inject constructor(
    private val awardService: AwardService
) {

    suspend fun createAward(request: PerformerPrizeRequest): PerformerPrizeResponse {
        return awardService.createAward(request)
    }

    suspend fun getPrizes(): List<PerformerPrizeResponse> {
        return awardService.getPrizes()
    }

    suspend fun getAwardById(id: Int): Award {
        return awardService.getAwardById(id).toDomain()
    }

    suspend fun addWinnerToAward(awardId: Int, artistId: Int) {
        awardService.addWinner(awardId, mapOf("artistId" to artistId))
    }
}
