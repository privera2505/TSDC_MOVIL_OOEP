package com.vinilo.domain.model

data class PerformerWinner(
    val id: Int,
    val name: String,
    val image: String,
    val premiationDate: String,
    val performer: Performer
)