package com.example.domain.satellites.model.usecase

import com.example.domain.satellites.model.SatelliteDetail
import com.example.domain.satellites.model.SatellitesRepo
import javax.inject.Inject

class GetSatelliteDetailUseCase @Inject constructor(
    private val satellitesRepo: SatellitesRepo
) {

    suspend operator fun invoke(satelliteId: Int): SatelliteDetail =
        satellitesRepo.getSatelliteDetail(satelliteId = satelliteId)
}