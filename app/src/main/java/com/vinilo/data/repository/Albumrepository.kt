package com.vinilo.data.repository

import com.vinilo.data.local.dao.AlbumDao
import com.vinilo.data.local.dao.TrackDao
import com.vinilo.data.local.entities.TrackEntity
import com.vinilo.data.remote.dto.AlbumDto
import com.vinilo.data.remote.service.AlbumService
import com.vinilo.domain.model.Album
import com.vinilo.utils.toDomain
import com.vinilo.utils.toEntity
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val albumDao: AlbumDao,
    private val trackDao: TrackDao,
    private val albumService: AlbumService
) {

    suspend fun getAlbums(): List<Album> {
        val local = albumDao.getAlbumsWithTracks()
        return if (local.isNotEmpty()) {
            local.map { it.toDomain() }
        } else {
            val remote = albumService.getAlbums()
            val albumEntities = remote.map { it.toEntity() }
            albumDao.insertAlbums(albumEntities)

            val trackEntities: List<TrackEntity> = remote.flatMap { dto ->
                dto.tracks.map { trackDto ->
                    TrackEntity(
                        albumId = dto.id,
                        name = trackDto.name,
                        duration = trackDto.duration
                    )
                }
            }
            trackDao.insertTracks(trackEntities)

            albumDao.getAlbumsWithTracks().map { it.toDomain() }
        }
    }


    suspend fun getAlbumById(id: Int): Album {
        val remote: AlbumDto = albumService.getAlbumById(id)
        val entity = remote.toEntity()
        albumDao.insertAlbums(listOf(entity))
        return entity.toDomain()
    }

}