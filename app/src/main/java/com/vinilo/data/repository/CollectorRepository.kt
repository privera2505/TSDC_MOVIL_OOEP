package com.vinilo.data.repository

import com.vinilo.data.local.dao.AlbumDao
import com.vinilo.data.local.dao.CollectorDao
import com.vinilo.data.local.dao.TrackDao
import com.vinilo.data.local.entities.TrackEntity
import com.vinilo.data.remote.service.AlbumService
import com.vinilo.data.remote.service.ApiClient
import com.vinilo.data.remote.service.CollectorService
import com.vinilo.domain.model.Collector
import com.vinilo.utils.toDomain
import com.vinilo.utils.toEntity
import javax.inject.Inject
import kotlin.collections.isNotEmpty
import kotlin.collections.map

class CollectorRepository @Inject constructor(
    private val collectorDao: CollectorDao,
    private val collectorService: CollectorService
) {

    /*private val collectorService: CollectorService =
        ApiClient.retrofit.create(CollectorService::class.java)*/

    suspend fun getCollectors(): List<Collector> {
        val local = collectorDao.getCollectors()
        return if (local.isNotEmpty()) {
            local.map { it.toDomain() }
        } else {
            val remote = collectorService.getCollectors()
            val collectorEntities = remote.map { it.toEntity() }
            collectorDao.insertCollectors(collectorEntities)


            collectorDao.getCollectors().map { it.toDomain() }
        }
    }

    suspend fun getCollectorById(id:Int): Collector{
        val local = collectorDao.getCollectorById(id)
        return if (local != null) {
            local.toDomain()
        } else {
            val remote = collectorService.getCollectorById(id)
            remote.toDomain()
        }
    }

}