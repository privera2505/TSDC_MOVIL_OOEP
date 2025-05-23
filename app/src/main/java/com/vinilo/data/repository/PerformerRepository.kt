package com.vinilo.data.repository

import com.vinilo.data.remote.service.ApiClient
import com.vinilo.data.remote.service.AwardService
import com.vinilo.data.remote.service.PerformerService
import com.vinilo.domain.model.Performer
import javax.inject.Inject

class PerformerRepository @Inject constructor(
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
        val all = performerService.getPerformers()
        val winners = awardService.getAwardWinners(prizeId).map { it.performer.id }
        return all.filter { it.id !in winners }
    }

}