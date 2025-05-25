package com.vinilo.data.local.dao

import androidx.room.*
import com.vinilo.data.local.entities.AwardEntity

@Dao
interface AwardDao {
    @Query("SELECT * FROM awards")
    suspend fun getAwards(): List<AwardEntity>

    @Query("DELETE FROM awards")
    suspend fun clearAwards()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAwards(awards: List<AwardEntity>)
}