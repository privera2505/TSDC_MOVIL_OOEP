package com.vinilo.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vinilo.domain.model.PerformerPrize

@Entity(tableName = "awards")
data class AwardEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val organization: String,
    val performerPrizes: List<PerformerPrize>
)