package com.vinilo.data.remote.dto

import com.vinilo.domain.model.CollectorAlbum
import com.vinilo.domain.model.Comment
import com.vinilo.domain.model.Performer

class CollectorDto (
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val comments: List<Comment>,
    val favoritePerformers: List<Performer>,
    val collectorAlbums: List<CollectorAlbum>
)