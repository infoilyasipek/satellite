package com.example.data.satellites.datasource.db

import com.example.data.satellites.datasource.SatellitesDataSource
import com.example.data.satellites.model.SatelliteDetailEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatelliteDBDataSource @Inject constructor(
    private val satelliteDao: SatelliteDao
) : SatellitesDataSource.DB {

    override suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailEntity? =
        withContext(Dispatchers.IO) { satelliteDao.getSatelliteDetail(satelliteId) }

    override suspend fun saveSatelliteDetail(satelliteDetail: SatelliteDetailEntity) {
        withContext(Dispatchers.IO) { satelliteDao.insert(satelliteDetail) }
    }
}