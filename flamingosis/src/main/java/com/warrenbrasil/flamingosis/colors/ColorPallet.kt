package com.warrenbrasil.flamingosis.colors

data class ColorPallet(
    val colors: List<ColorValues>
)

data class ColorValues(val colorName: String,
                       val light: String,
                       val dark: String)
