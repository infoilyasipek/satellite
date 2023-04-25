package com.example.domain.satellites.model

interface SatellitesRepo {

    suspend fun getSatellites(): List<Satellite>

    suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetail

    suspend fun getSatellitePosition(satelliteId: Int): List<SatellitePosition>
}