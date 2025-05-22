package com.vinilo.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vinilo.domain.model.CollectorAlbum
import com.vinilo.domain.model.Comment
import com.vinilo.domain.model.Performer

@Entity(tableName = "collectors")
class CollectorEntity (
    @PrimaryKey val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val comments: List<Comment>,
    val favoritePerformers: List<Performer>,
    val collectorAlbums: List<CollectorAlbum>
)
