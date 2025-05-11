package com.vinilo.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TrackEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val albumId: Int,
    val name: String,
    val duration: String
)