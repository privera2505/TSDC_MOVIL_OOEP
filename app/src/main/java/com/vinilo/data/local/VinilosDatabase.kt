package com.vinilo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vinilo.data.local.dao.AlbumDao
import com.vinilo.data.local.dao.CollectorDao
import com.vinilo.data.local.dao.TrackDao
import com.vinilo.data.local.entities.AlbumEntity
import com.vinilo.data.local.entities.CollectorEntity
import com.vinilo.data.local.entities.TrackEntity
import com.vinilo.utils.Converters

@Database(
    entities = [
        AlbumEntity::class,
        TrackEntity::class,
        CollectorEntity::class
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class VinilosDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
    abstract fun trackDao(): TrackDao
    abstract fun collectorDao(): CollectorDao
}
