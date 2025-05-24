package com.vinilo.di

import android.content.Context
import androidx.room.Room
import com.vinilo.data.local.VinilosDatabase
import com.vinilo.data.local.dao.AlbumDao
import com.vinilo.data.local.dao.AwardDao
import com.vinilo.data.local.dao.CollectorDao
import com.vinilo.data.local.dao.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): VinilosDatabase {

        return Room.databaseBuilder(
            context,
            VinilosDatabase::class.java,
            "vinilos_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAlbumDao(db: VinilosDatabase): AlbumDao = db.albumDao()

    @Provides
    fun provideTrackDao(db: VinilosDatabase): TrackDao = db.trackDao()

    @Provides
    fun provideCollectorDao(db: VinilosDatabase): CollectorDao = db.collectorDao()

    @Provides
    fun provideAwardDao(db: VinilosDatabase): AwardDao = db.awardDao()

}
