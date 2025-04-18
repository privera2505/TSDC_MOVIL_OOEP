package com.vinilo.model

import java.util.Date

data class Performer (
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: Date
)