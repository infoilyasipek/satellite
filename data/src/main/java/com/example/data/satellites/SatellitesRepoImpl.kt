package com.example.data.satellites

import com.example.data.satellites.datasource.SatellitesDataSource
import com.example.data.satellites.model.SatelliteResponse
import com.example.data.satellites.model.toDomain
import com.example.domain.satellites.model.Satellite
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
}