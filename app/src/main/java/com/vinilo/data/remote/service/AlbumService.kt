package com.vinilo.data.remote.service

import com.vinilo.data.remote.dto.AlbumDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): List<AlbumDto>

    @GET("albums/{id}")
    suspend fun getAlbumById(@Path("id") albumId: Int): AlbumDto
}