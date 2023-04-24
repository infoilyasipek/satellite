package com.example.domain.satellites.model

data class Satellite(
    val id: Int,
    val isActive: Boolean,
    val name: String
)

val satelliteSample: Satellite = Satellite(
    id = 1,
    isActive = true,
    name = "Satellite 1"
)
