package com.example.domain.satellites.usecase

import com.example.domain.satellites.model.SatellitePosition
import com.example.domain.satellites.model.SatellitesRepo
import javax.inject.Inject

class GetSatellitePositionsUseCase @Inject constructor(
    private val satellitesRepo: SatellitesRepo
) {

    suspend operator fun invoke(satelliteId: Int): List<SatellitePosition> =
        satellitesRepo.getSatellitePosition(satelliteId = satelliteId)
}