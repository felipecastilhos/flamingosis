package com.warrenbrasil.flamingosis.colors

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import com.google.gson.Gson
import com.warrenbrasil.flamingosis.sharedpreferences.get
import com.warrenbrasil.flamingosis.sharedpreferences.getPrefs
import com.warrenbrasil.flamingosis.sharedpreferences.set
import com.warrenbrasil.flamingosis.theme.ThemeModel
import com.warrenbrasil.flamingosis.toJson

class ColorLoader {
    fun loadColorList(prefs: SharedPreferences): ThemeModel {
        val colorPalletJsonString = prefs[PREFS_COLOR_PALLET, ""]
        val gson = Gson()
        val colorPallet = gson.fromJson<ColorPallet>(colorPalletJsonString, ColorPallet::class.java)
        return colorPallet.toTheme()
    }


    fun saveColors(context: Context, pallet: ColorPallet) {
        context.getPrefs()[PREFS_COLOR_PALLET] = pallet.toJson()
    }


    companion object {
        const val PREFS_COLOR_PALLET = "pallet"
    }

}


fun ColorPallet.toTheme(): ThemeModel {
    val theme = ThemeModel()

    colors.forEach {
        when(it.colorName) {
            "accountAccent" -> {
                theme.accountAccentLight = createDynamicColor(it.light)
                theme.accountAccentDark = createDynamicColor(it.dark)
            }
            "accountOverAccent" -> {
                theme.accountOverAccentLight = createDynamicColor(it.light)
                theme.accountOverAccentDark = createDynamicColor(it.dark)
            }
        }
    }

    return theme
}

private fun createDynamicColor(hexColor: String): DynamicColor {
    val dynamicColor = DynamicColor()
    dynamicColor.setColor(Color.parseColor(hexColor))
    return dynamicColor
}
