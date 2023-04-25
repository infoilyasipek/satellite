package com.example.data.satellites.datasource

import com.example.data.satellites.model.SatelliteDetailEntity
import com.example.data.satellites.model.SatelliteDetailResponse
import com.example.data.satellites.model.SatellitePositionResponse
import com.example.data.satellites.model.SatelliteResponse

interface SatellitesDataSource {
    interface Asset {
        suspend fun getSatellites(): List<SatelliteResponse>

        suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailResponse

        suspend fun getSatellitePosision(satelliteId: Int): SatellitePositionResponse
    }

    interface DB {
        suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailEntity?

        suspend fun saveSatelliteDetail(satelliteDetail: SatelliteDetailEntity)
    }
}