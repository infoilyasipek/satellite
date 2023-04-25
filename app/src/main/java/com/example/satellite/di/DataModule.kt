package com.example.satellite.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.SatelliteDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context = context,
        klass = SatelliteDB::class.java,
        name = "satellite.db"
    ).build()

    @Singleton
    @Provides
    fun provideSatelliteDao(
        appDatabase: SatelliteDB
    ) = appDatabase.satelliteDao()
}