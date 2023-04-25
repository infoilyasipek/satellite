package com.example.data.utils

import android.content.res.AssetManager
import com.google.gson.reflect.TypeToken

inline fun <reified R> AssetManager.readAssetFile(fileName: String): R {
    val jsonString = this
        .open(fileName)
        .bufferedReader()
        .use { it.readText() }
    val returnType = object : TypeToken<R>() {}.type
    return GsonHolder.gson.fromJson(jsonString, returnType)
}