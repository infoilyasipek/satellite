package com.example.data.satellites.model

import com.example.domain.satellites.model.SatelliteDetail
import com.google.gson.annotations.SerializedName

data class SatelliteDetailResponse(
    @SerializedName("cost_per_launch") val costPerLaunch: Int,
    @SerializedName("first_flight") val firstFlight: String,
    @SerializedName("height") val height: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("mass") val mass: Int
)

fun SatelliteDetailResponse.toDomain() = SatelliteDetail(
    costPerLaunch = costPerLaunch,
    firstFlight = firstFlight,
    height = height,
    id = id,
    mass = mass
)