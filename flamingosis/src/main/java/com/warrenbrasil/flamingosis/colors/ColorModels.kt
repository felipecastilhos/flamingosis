package com.warrenbrasil.flamingosis.colors

import android.content.Context
import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import com.warrenbrasil.flamingosis.ViewResource

sealed class ViewColor : ViewResource() {
    @ColorInt
    abstract fun getColorCode(context: Context): Int
}

data class ColorResource(@ColorRes var colorRes: Int) : ViewColor() {
    override fun getColorCode(context: Context): Int {
        return context.getColorCode(colorRes)
    }
}

data class ColorCode(@ColorInt var color: Int) : ViewColor() {
    override fun getColorCode(context: Context): Int {
        return color
    }
}

data class TintList(var colorStateList: ColorStateList? = null) : ViewColor() {
    override fun getColorCode(context: Context): Int {
        return colorStateList?.defaultColor ?: 0
    }
}
