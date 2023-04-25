package com.example.domain.satellites.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Satellite(
    val id: Int,
    val isActive: Boolean,
    val name: String
) : Parcelable

val satelliteSample: Satellite = Satellite(
    id = 1,
    isActive = true,
    name = "Satellite 1"
)
