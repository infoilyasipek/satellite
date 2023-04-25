package com.example.data.satellites.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.satellites.model.SatelliteDetail

@Entity
data class SatelliteDetailEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "cost_per_launch") val costPerLaunch: Int,
    @ColumnInfo(name = "first_flight") val firstFlight: String,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "mass") val mass: Int
)

fun SatelliteDetailEntity.toDomain() = SatelliteDetail(
    costPerLaunch = costPerLaunch,
    firstFlight = firstFlight,
    height = height,
    id = id,
    mass = mass
)

fun SatelliteDetail.toEntity() = SatelliteDetailEntity(
    costPerLaunch = costPerLaunch,
    firstFlight = firstFlight,
    height = height,
    id = id,
    mass = mass
)