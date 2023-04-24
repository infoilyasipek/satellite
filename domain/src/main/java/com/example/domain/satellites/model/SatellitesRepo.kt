package com.example.domain.satellites.model

interface SatellitesRepo {

    suspend fun getSatellites(): List<Satellite>
}