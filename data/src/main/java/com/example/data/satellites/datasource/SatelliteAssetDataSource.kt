package com.example.data.satellites.datasource

import android.content.Context
import com.example.data.satellites.model.SatelliteResponse
import com.example.data.utils.GsonHolder
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SatelliteAssetDataSource @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : SatellitesDataSource.Asset {
    override suspend fun getSatellites(): List<SatelliteResponse> = withContext(Dispatchers.IO) {
        // delay to simulate network request
        delay(3000)
        val jsonString = applicationContext.assets
            .open("satellites/satellites.json")
            .bufferedReader()
            .use { it.readText() }
        val listCountryType = object : TypeToken<List<SatelliteResponse>>() {}.type
        GsonHolder.gson.fromJson(jsonString, listCountryType)
    }

}