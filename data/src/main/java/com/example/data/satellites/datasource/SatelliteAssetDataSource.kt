package com.example.data.satellites.datasource

import android.content.Context
import com.example.data.satellites.model.SatelliteDetailResponse
import com.example.data.satellites.model.SatellitePositionResponse
import com.example.data.satellites.model.SatellitePositionsResponseWrapper
import com.example.data.satellites.model.SatelliteResponse
import com.example.data.utils.readAssetFile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatelliteAssetDataSource @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : SatellitesDataSource.Asset {

    override suspend fun getSatellites(): List<SatelliteResponse> = withContext(Dispatchers.IO) {
        // delay to simulate network request
        delay(3000)

        applicationContext
            .assets
            .readAssetFile("satellites/satellites.json")
    }

    override suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailResponse =
        withContext(Dispatchers.IO) {
            // delay to simulate network request
            delay(3000)

            val details = applicationContext
                .assets
                .readAssetFile<List<SatelliteDetailResponse>>("satellites/satellites_detail.json")

            details.first { it.id == satelliteId }
        }

    override suspend fun getSatellitePosision(satelliteId: Int): SatellitePositionResponse {
        return withContext(Dispatchers.IO) {
            // delay to simulate network request
            delay(1000)

            val satellitePositionsResponseWrapper = applicationContext
                .assets
                .readAssetFile<SatellitePositionsResponseWrapper>("satellites/positions.json")

            satellitePositionsResponseWrapper.items.first { it.satelliteId == satelliteId.toString() }
        }
    }
}