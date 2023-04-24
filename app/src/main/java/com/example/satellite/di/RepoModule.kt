package com.example.satellite.di

import com.example.data.satellites.SatellitesRepoImpl
import com.example.domain.satellites.model.SatellitesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideSatellitesRepo(
        satellitesRepoImpl: SatellitesRepoImpl
    ): SatellitesRepo = satellitesRepoImpl
}