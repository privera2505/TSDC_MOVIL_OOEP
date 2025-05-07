package com.vinilo.data.remote.dto

import java.util.Date

data class AlbumDto(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: Date,
    val description: String,
    val genre: String,
    val recordLabel: String,
    val tracks: List<TrackDto>
)