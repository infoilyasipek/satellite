package com.example.data.satellites

import com.example.data.satellites.datasource.SatellitesDataSource
import com.example.data.satellites.model.PositionResponse
import com.example.data.satellites.model.SatelliteResponse
import com.example.data.satellites.model.toDomain
import com.example.data.satellites.model.toEntity
import com.example.domain.satellites.model.Satellite
import com.example.domain.satellites.model.SatelliteDetail
import com.example.domain.satellites.model.SatellitePosition
import com.example.domain.satellites.model.SatellitesRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatellitesRepoImpl @Inject constructor(
    private val assetSource: SatellitesDataSource.Asset,
    private val dbSource: SatellitesDataSource.DB
) : SatellitesRepo {

    override suspend fun getSatellites(): List<Satellite> {
        return assetSource.getSatellites().map(SatelliteResponse::toDomain)
    }

    override suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetail {
        val satelliteDetailFromDB = dbSource.getSatelliteDetail(satelliteId)
        if (satelliteDetailFromDB != null) return satelliteDetailFromDB.toDomain()

        val satelliteDetail = assetSource.getSatelliteDetail(satelliteId).toDomain().also {
            dbSource.saveSatelliteDetail(it.toEntity())
        }
        return satelliteDetail
    }

    override suspend fun getSatellitePosition(satelliteId: Int): List<SatellitePosition> {
        val positions = assetSource.getSatellitePosision(satelliteId).positions
        return positions.map(PositionResponse::toDomain)
    }
}