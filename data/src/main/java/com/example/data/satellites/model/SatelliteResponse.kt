package com.example.data.satellites.model

import com.example.domain.satellites.model.Satellite
import com.google.gson.annotations.SerializedName

data class SatelliteResponse(
    @SerializedName("active") val isActive: Boolean,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

fun SatelliteResponse.toDomain() = Satellite(
    isActive = isActive,
    id = id,
    name = name
)