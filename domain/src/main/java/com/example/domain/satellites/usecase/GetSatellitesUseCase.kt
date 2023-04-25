package com.example.domain.satellites.usecase

import com.example.domain.satellites.model.Satellite
import com.example.domain.satellites.model.SatellitesRepo
import javax.inject.Inject

class GetSatellitesUseCase @Inject constructor(
    private val satellitesRepo: SatellitesRepo
) {

    suspend operator fun invoke(): List<Satellite> = satellitesRepo.getSatellites()
}