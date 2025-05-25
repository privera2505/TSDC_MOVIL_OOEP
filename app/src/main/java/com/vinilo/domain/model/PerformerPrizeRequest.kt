package com.vinilo.domain.model

data class PerformerPrizeResponse (

    val id: Int,
    val name: String,
    val description: String,
    val organization: String,
    val performerPrizes: List<PerformerPrize>?

)

data class PerformerPrizeRequest (

    val name: String,
    val description: String,
    val organization: String

)