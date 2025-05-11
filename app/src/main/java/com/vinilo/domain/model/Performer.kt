package com.vinilo.domain.model

import java.util.Date

data class Performer (
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: Date,
    val albums: List<Album>,
    val performerPrizes: List<Performer>
)