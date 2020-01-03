package com.warrenbrasil.flamingosis.colors

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

@ColorInt
fun Context.getColorCode(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}