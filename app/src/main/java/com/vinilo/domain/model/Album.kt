package com.vinilo.domain.model

import java.util.Date

data class Album(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: Date,
    val description: String,
    val genre: String,
    val recordLabel: String,
    val performers: List<Performer>,
    val tracks: List<Track>,
    val comments: List<Comment>
)