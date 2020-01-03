package com.warrenbrasil.flamingosis.json

import android.content.Context


private fun Context.readJsonFile(pathName: String, fileName: String): String {
    val fileCode = readFileFromAssets("$pathName/$fileName.json")!!
    return "this.$fileName = $fileCode"
}

fun Context.readFileFromAssets(fileName: String): String? {
    val inputStream = assets.open(fileName)
    return inputStream.use {
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    }
}