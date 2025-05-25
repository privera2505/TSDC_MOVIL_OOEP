package com.vinilo.data.remote.dto

import com.vinilo.domain.model.PerformerPrize

data class AwardReponseDto (
    val id: Int,
    val name: String,
    val description: String,
    val organization: String,
    val performerPrizes: List<PerformerPrize>?
)