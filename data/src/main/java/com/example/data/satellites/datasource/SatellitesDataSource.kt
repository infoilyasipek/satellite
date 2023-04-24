package com.example.data.satellites.datasource

import com.example.data.satellites.model.SatelliteResponse

interface SatellitesDataSource {
    interface Asset {
        suspend fun getSatellites(): List<SatelliteResponse>
    }
}