package com.vinilo.model

import com.vinilo.enums.Genre
import com.vinilo.enums.RecordLabel
import java.util.Date

data class Album (
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: Date,
    val description: String,
    val genre: Genre,
    val recordLabel: RecordLabel,
    val performers: List<Performer>,
    val tracks: List<Track>,
    val comments: List<Comment>
)