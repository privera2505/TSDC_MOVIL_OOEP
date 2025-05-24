package com.vinilo.data.repository

import com.vinilo.data.remote.dto.PerformanceRequestDto
import com.vinilo.data.remote.service.AwardService
import com.vinilo.data.remote.service.PerformerService
import com.vinilo.domain.model.Performer

class PerformerRepository(
    private val performerService: PerformerService,
    private val awardService: AwardService
) {

    suspend fun getPerformers(): List<Performer> {
        return performerService.getPerformers()
    }

    suspend fun getPerformerById(id: Int): Performer {
        return performerService.getPerformerById(id)
    }

    suspend fun getPerformersNotInPrize(prizeId: Int): List<Performer> {
        val allPerformers = performerService.getPerformers()
        val prizeWinners = awardService.getAwardWinners(prizeId)
            .map { it.performer.id }

        return allPerformers.filter { it.id !in prizeWinners }
    }

    suspend fun addWinnerToPrize(prizeId: Int, artistId: Int, date: String) {
        val request = PerformanceRequestDto(premiationDate = date)
        awardService.addWinner(prizeId, artistId, request)
    }
}
