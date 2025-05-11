package com.vinilo.data.local.dao

import androidx.room.*
import com.vinilo.data.local.entities.AlbumEntity
import com.vinilo.data.local.entities.AlbumWithTracks

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums")
    suspend fun getAlbums(): List<AlbumEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums: List<AlbumEntity>)
    @Transaction
    @Query("SELECT * FROM albums")
    suspend fun getAlbumsWithTracks(): List<AlbumWithTracks>

    @Transaction
    @Query("SELECT * FROM albums WHERE id = :albumId")
    suspend fun getAlbumWithTracksById(albumId: Int): AlbumWithTracks?


    @Query("DELETE FROM albums")
    suspend fun clearAlbums()
}