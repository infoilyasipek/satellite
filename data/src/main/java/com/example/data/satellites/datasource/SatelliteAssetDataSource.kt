package com.example.data.satellites.datasource

import android.content.Context
import com.example.data.satellites.model.SatelliteDetailResponse
import com.example.data.satellites.model.SatellitePositionResponse
import com.example.data.satellites.model.SatellitePositionsResponseWrapper
import com.example.data.satellites.model.SatelliteResponse
import com.example.data.utils.Constants.SATELLITES_ASSET_PATH
import com.example.data.utils.Constants.SATELLITES_DETAIL_ASSET_PATH
import com.example.data.utils.Constants.SATELLITES_POSITIONS_ASSET_PATH
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
            .readAssetFile(fileName = SATELLITES_ASSET_PATH)
    }

    override suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailResponse =
        withContext(Dispatchers.IO) {
            // delay to simulate network request
            delay(3000)

            val details = applicationContext
                .assets
                .readAssetFile<List<SatelliteDetailResponse>>(fileName = SATELLITES_DETAIL_ASSET_PATH)

            details.first { it.id == satelliteId }
        }

    override suspend fun getSatellitePosision(satelliteId: Int): SatellitePositionResponse {
        return withContext(Dispatchers.IO) {
            // delay to simulate network request
            delay(1000)

            val satellitePositionsResponseWrapper = applicationContext
                .assets
                .readAssetFile<SatellitePositionsResponseWrapper>(fileName = SATELLITES_POSITIONS_ASSET_PATH)

            satellitePositionsResponseWrapper.items.first { it.satelliteId == satelliteId.toString() }
        }
    }
}