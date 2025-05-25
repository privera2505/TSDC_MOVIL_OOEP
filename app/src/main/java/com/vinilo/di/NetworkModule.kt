package com.vinilo.di

import com.vinilo.data.remote.service.AlbumService
import com.vinilo.data.remote.service.ApiClient
import com.vinilo.data.remote.service.AwardService
import com.vinilo.data.remote.service.CollectorService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return ApiClient.httpClient
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://backvynils-production.up.railway.app/")
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAlbumService(retrofit: Retrofit): AlbumService =
        retrofit.create(AlbumService::class.java)

    @Provides
    @Singleton
    fun provideCollectorService(retrofit: Retrofit): CollectorService {
        return retrofit.create(CollectorService::class.java)
    }

    @Provides
    @Singleton
    fun provideAwardService(retrofit: Retrofit): AwardService {
        return retrofit.create(AwardService::class.java)
    }
}
