package com.example.data.satellites.model

import com.example.domain.satellites.model.SatellitePosition
import com.google.gson.annotations.SerializedName

data class SatellitePositionsResponseWrapper(
    @SerializedName("list") val items: List<SatellitePositionResponse>,
)

data class SatellitePositionResponse(
    @SerializedName("id") val satelliteId: String,
    @SerializedName("positions") val positions: List<PositionResponse>,
)

data class PositionResponse(
    @SerializedName("posX") val posX: Double,
    @SerializedName("posY") val posY: Double,
)

fun PositionResponse.toDomain() = SatellitePosition(
    posX = posX,
    posY = posY,
)