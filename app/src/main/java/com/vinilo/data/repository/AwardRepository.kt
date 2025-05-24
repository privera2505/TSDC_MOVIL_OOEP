package com.vinilo.data.repository

import com.vinilo.data.local.dao.AwardDao
import com.vinilo.data.remote.dto.AwardReponseDto
import com.vinilo.data.remote.service.AwardService
import com.vinilo.domain.model.Award
import com.vinilo.domain.model.PerformerPrizeRequest
import com.vinilo.domain.model.PerformerPrizeResponse
import com.vinilo.domain.model.PerformerWinner
import com.vinilo.utils.toDomain
import com.vinilo.utils.toEntity
import javax.inject.Inject

class AwardRepository @Inject constructor(
    private val awardDao: AwardDao,
    private val awardService: AwardService
) {

    suspend fun createAward(request: PerformerPrizeRequest): AwardReponseDto {
        return awardService.createAward(request)
    }

    suspend fun getPrizes(): List<PerformerPrizeResponse> {
        val local = awardDao.getAwards()

        return if (local.isNotEmpty()){
            local.map { it.toDomain() }
        } else  {
            val remote = awardService.getPrizes()
            val awardEntities = remote.map { it.toEntity() }
            awardDao.insertAwards(awardEntities)

            awardDao.getAwards().map { it.toDomain() }
        }



    }

    suspend fun getAwardById(id: Int): Award {
        return awardService.getPrizeById(id).toDomain()
    }

    suspend fun getAwardWinners(awardId: Int): List<PerformerWinner> {
        return awardService.getAwardWinners(awardId).map { it.toDomain() }
    }
}