package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.satellites.datasource.db.SatelliteDao
import com.example.data.satellites.model.SatelliteDetailEntity

@Database(entities = [SatelliteDetailEntity::class], version = 1)
abstract class SatelliteDB : RoomDatabase() {
    abstract fun satelliteDao(): SatelliteDao
}