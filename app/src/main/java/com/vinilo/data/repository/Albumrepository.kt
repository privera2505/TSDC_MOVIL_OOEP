package com.vinilo.data.repository

import com.vinilo.data.remote.services.AlbumService
import com.vinilo.data.remote.services.ApiClient
import com.vinilo.domain.model.Album

class AlbumRepository {

    private val albumService: AlbumService =
        ApiClient.retrofit.create(AlbumService::class.java)

    suspend fun getAlbums(): List<Album> {
        return albumService.getAlbums()
    }

    suspend fun getAlbumById(id: Int): Album {
        return albumService.getAlbumById(id)
    }
}
