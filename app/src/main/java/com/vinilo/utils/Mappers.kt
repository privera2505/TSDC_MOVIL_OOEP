package com.vinilo.utils

import com.vinilo.data.remote.dto.AlbumDto
import com.vinilo.data.local.entities.AlbumEntity
import com.vinilo.data.local.entities.AlbumWithTracks
import com.vinilo.data.local.entities.TrackEntity
import com.vinilo.domain.model.Album
import com.vinilo.domain.model.Track

fun AlbumDto.toEntity(): AlbumEntity = AlbumEntity(
    id = id,
    name = name,
    cover = cover,
    releaseDate = releaseDate,
    description = description,
    genre = genre,
    recordLabel = recordLabel
)

fun AlbumEntity.toDomain(): Album = Album(
    id = id,
    name = name,
    cover = cover,
    releaseDate = releaseDate,
    description = description,
    genre = genre,
    recordLabel = recordLabel,
    performers = emptyList(),
    tracks = emptyList(),
    comments = emptyList(),
)

fun TrackEntity.toDomain(): Track = Track(
    name = name,
    duration = duration,
)

fun AlbumWithTracks.toDomain(): Album = Album(
    id = album.id,
    name = album.name,
    cover = album.cover,
    releaseDate = album.releaseDate,
    description = album.description,
    genre = album.genre,
    recordLabel = album.recordLabel,
    tracks = tracks.map { it.toDomain() },
    performers = emptyList(),
    comments = emptyList()
)