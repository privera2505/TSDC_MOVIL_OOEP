package com.vinilo.repository

import com.vinilo.network.AlbumService
import com.vinilo.network.ApiClient
import com.vinilo.model.Album

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
