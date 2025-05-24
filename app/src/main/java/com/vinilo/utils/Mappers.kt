package com.vinilo.utils

import com.vinilo.data.remote.dto.AlbumDto
import com.vinilo.data.local.entities.AlbumEntity
import com.vinilo.data.local.entities.AlbumWithTracks
import com.vinilo.data.local.entities.CollectorEntity
import com.vinilo.data.local.entities.TrackEntity
import com.vinilo.data.remote.dto.AwardDto
import com.vinilo.data.remote.dto.CollectorDto
import com.vinilo.data.remote.dto.PerformerDto
import com.vinilo.data.remote.dto.PerformerWinnerDto
import com.vinilo.domain.model.Album
import com.vinilo.domain.model.Award
import com.vinilo.domain.model.Collector
import com.vinilo.domain.model.Performer
import com.vinilo.domain.model.PerformerWinner
import com.vinilo.domain.model.Track
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


private val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
    timeZone = TimeZone.getTimeZone("UTC")
}

fun parseDate(dateString: String): Date {
    return try {
        isoDateFormat.parse(dateString) ?: Date()
    } catch (e: Exception) {
        Date()
    }
}

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


fun CollectorDto.toEntity(): CollectorEntity = CollectorEntity(
    id = this.id,
    name = this.name,
    telephone = this.telephone,
    email = this.email,
    comments = this.comments,
    favoritePerformers = this.favoritePerformers,
    collectorAlbums = this.collectorAlbums
)


fun CollectorEntity.toDomain(): Collector = Collector(
    id = this.id,
    name = this.name,
    telephone = this.telephone,
    email = this.email,
    comments = this.comments,
    favoritePerformers = this.favoritePerformers,
    collectorAlbums = this.collectorAlbums
)

fun CollectorDto.toDomain(): Collector = Collector(
    id = this.id,
    name = this.name,
    telephone = this.telephone,
    email = this.email,
    comments = this.comments,
    favoritePerformers = this.favoritePerformers,
    collectorAlbums = this.collectorAlbums
)

fun AwardDto.toDomain(): Award {
    return Award(
        id = this.id,
        name = this.name,
        description = this.description ?: "",
        organization = this.organization ?: "",
    )
}

fun PerformerWinnerDto.toDomain(): PerformerWinner {
    val performerDomain = performer.toDomain()
    return PerformerWinner(
        id = performer.id,
        name = performer.name,
        image = performer.image,
        premiationDate = premiationDate,
        performer = performerDomain
    )
}

fun PerformerDto.toDomain(): Performer = Performer(
    id = id,
    name = name,
    image = image,
    description = description,
    birthDate = parseDate(birthDate),
    albums = emptyList(),
    performerPrizes = emptyList()
)

