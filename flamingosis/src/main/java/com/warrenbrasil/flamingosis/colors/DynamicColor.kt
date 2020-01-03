package com.warrenbrasil.flamingosis.colors

import android.content.Context
import androidx.annotation.ColorRes

class DynamicColor {
    private var colorResource: Int? = null
    private var colorCode: Int? = null

    fun setColor(colorCode: Int?) {
        colorResource = null
        this.colorCode = colorCode
    }

    fun setColorResource(@ColorRes colorResource: Int?) {
        colorCode = null
        this.colorResource = colorResource
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