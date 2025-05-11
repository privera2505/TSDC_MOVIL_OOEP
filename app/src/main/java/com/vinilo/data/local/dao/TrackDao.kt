package com.vinilo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vinilo.data.local.entities.TrackEntity

@Dao
interface TrackDao {
    @Query("SELECT * FROM tracks WHERE albumId = :albumId")
    suspend fun getTracksForAlbum(albumId: Int): List<TrackEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracks(tracks: List<TrackEntity>)
}