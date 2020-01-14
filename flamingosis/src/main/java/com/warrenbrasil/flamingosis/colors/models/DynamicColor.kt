package com.warrenbrasil.flamingosis.colors.models

import android.content.Context
import com.warrenbrasil.flamingosis.colors.getColorCode

class DynamicColor {
    private var colorResource: Int? = null
    private var colorCode: Int? = null

    fun setColor(colorCode: Int?) {
        colorResource = null
        this.colorCode = colorCode
    }

    fun getColorCode(context: Context): Int? {
        return when {
            colorCode != null -> colorCode
            colorResource != null -> colorResource?.let {
                context.getColorCode(it)
            }
            else -> null
        }
    }
}