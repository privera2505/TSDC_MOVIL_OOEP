package com.vinilo.data.repository

import com.vinilo.data.remote.service.AwardService
import com.vinilo.domain.model.Award
import com.vinilo.domain.model.PerformerPrizeRequest
import com.vinilo.domain.model.PerformerPrizeResponse
import com.vinilo.domain.model.PerformerWinner
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
        return awardService.getPrizeById(id).toDomain()
    }

    suspend fun getAwardWinners(awardId: Int): List<PerformerWinner> {
        return awardService.getAwardWinners(awardId).map { it.toDomain() }
    }

    suspend fun addWinnerToPrize(prizeId: Int, artistId: Int) {
        awardService.addWinner(prizeId, artistId)
    }

}