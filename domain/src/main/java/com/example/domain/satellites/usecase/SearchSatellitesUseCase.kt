package com.example.domain.satellites.usecase

import com.example.domain.satellites.model.Satellite
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchSatellitesUseCase @Inject constructor() {

    suspend operator fun invoke(
        allSatellites: List<Satellite>,
        query: String
    ): List<Satellite> {
        if (query.isEmpty()) return allSatellites

        delay(1000)

        return allSatellites.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}