package com.example.satellite.di

import com.example.data.satellites.datasource.SatellitesDataSource
import com.example.data.satellites.datasource.asset.SatelliteAssetDataSource
import com.example.data.satellites.datasource.db.SatelliteDBDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideSatellitesAssetDataSource(
        satelliteAssetDataSource: SatelliteAssetDataSource
    ): SatellitesDataSource.Asset = satelliteAssetDataSource

    @Provides
    fun provideSatellitesDbDataSource(
        satelliteDbDataSource: SatelliteDBDataSource
    ): SatellitesDataSource.DB = satelliteDbDataSource
}