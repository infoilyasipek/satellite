package com.example.data.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonHolder {

    val gson: Gson by lazy {
        GsonBuilder().create()
    }
}