package com.vinilo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vinilo.data.local.entities.AlbumEntity
import com.vinilo.data.local.entities.AlbumWithTracks
import com.vinilo.data.local.entities.CollectorEntity

@Dao
interface CollectorDao {
    @Query("SELECT * FROM collectors")
    suspend fun getCollectors(): List<CollectorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollectors(collectors: List<CollectorEntity>)

    @Query("DELETE FROM collectors")
    suspend fun clearCollectors()

    @Query("SELECT * FROM collectors WHERE id = :collectorId")
    suspend fun getCollectorById(collectorId: Int): CollectorEntity?
}