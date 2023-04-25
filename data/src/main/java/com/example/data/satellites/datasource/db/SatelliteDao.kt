package com.example.data.satellites.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.satellites.model.SatelliteDetailEntity

@Dao
interface SatelliteDao {

    @Query("SELECT * FROM SatelliteDetailEntity WHERE id = :satelliteId LIMIT 1")
    fun getSatelliteDetail(satelliteId: Int): SatelliteDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(satelliteDetail: SatelliteDetailEntity)

}