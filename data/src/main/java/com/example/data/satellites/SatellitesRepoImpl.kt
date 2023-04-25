package com.example.data.satellites

import com.example.data.satellites.datasource.SatellitesDataSource
import com.example.data.satellites.model.PositionResponse
import com.example.data.satellites.model.SatelliteResponse
import com.example.data.satellites.model.toDomain
import com.example.domain.satellites.model.Satellite
import com.example.domain.satellites.model.SatelliteDetail
import com.example.domain.satellites.model.SatellitePosition
import com.example.domain.satellites.model.SatellitesRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SatellitesRepoImpl @Inject constructor(
    private val satellitesAssetDataSource: SatellitesDataSource.Asset
) : SatellitesRepo {

    override suspend fun getSatellites(): List<Satellite> {
        return satellitesAssetDataSource.getSatellites().map(SatelliteResponse::toDomain)
    }

    override suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetail {
        return satellitesAssetDataSource.getSatelliteDetail(satelliteId).toDomain()
    }

    override suspend fun getSatellitePosition(satelliteId: Int): List<SatellitePosition> {
        val positions = satellitesAssetDataSource.getSatellitePosision(satelliteId).positions
        return positions.map(PositionResponse::toDomain)
    }
}