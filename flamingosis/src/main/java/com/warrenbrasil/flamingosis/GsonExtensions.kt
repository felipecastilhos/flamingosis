package com.warrenbrasil.flamingosis

import com.google.gson.Gson

fun <T> T.toJson(): String? {
    val gson = Gson()
    return gson.toJson(this)
}


fun <T> String.fromJson(typeOfT: Class<T>): T {
    val gson = Gson()
    return gson.fromJson(this, typeOfT)
}

