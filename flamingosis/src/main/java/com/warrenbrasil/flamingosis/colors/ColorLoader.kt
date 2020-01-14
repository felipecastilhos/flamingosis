package com.warrenbrasil.flamingosis.colors

import android.content.Context
import android.graphics.Color
import com.warrenbrasil.flamingosis.colors.models.ColorPallet
import com.warrenbrasil.flamingosis.colors.models.DynamicColor
import com.warrenbrasil.flamingosis.fromJson
import com.warrenbrasil.flamingosis.sharedpreferences.get
import com.warrenbrasil.flamingosis.sharedpreferences.getPrefs
import com.warrenbrasil.flamingosis.sharedpreferences.set
import com.warrenbrasil.flamingosis.theme.ThemeModel
import com.warrenbrasil.flamingosis.toJson

class ColorLoader {
    fun loadColorList(context: Context): ThemeModel? {
        val colorPalletJsonString = context.getPrefs()[PREFS_COLOR_PALLET, ""]
        val colorPallet = colorPalletJsonString?.fromJson(ColorPallet::class.java)
        return colorPallet?.toTheme()
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
        when (it.colorName) {
            "accountAccent" -> {
                theme.accountAccentLight = createDynamicColor(it.light)
                theme.accountAccentDark = createDynamicColor(it.dark)
            }

            "accountOverAccent" -> {
                theme.accountOverAccentLight = createDynamicColor(it.light)
                theme.accountOverAccentDark = createDynamicColor(it.dark)
            }

            "cashAccent" -> {
                theme.cashAccentLight = createDynamicColor(it.light)
                theme.cashAccentDark = createDynamicColor(it.dark)
            }

            "cashOverAccent" -> {
                theme.cashOverAccentLight = createDynamicColor(it.light)
                theme.cashOverAccentDark = createDynamicColor(it.dark)
            }

            "wealthAccent" -> {
                theme.wealthAccentLight = createDynamicColor(it.light)
                theme.wealthAccentDark = createDynamicColor(it.dark)
            }

            "wealthOverAccent" -> {
                theme.wealthOverAccentDark = createDynamicColor(it.light)
                theme.wealthOverAccentDark = createDynamicColor(it.dark)
            }

            "tradingAccent" -> {
                theme.tradingAccentLight = createDynamicColor(it.light)
                theme.tradingAccentDark = createDynamicColor(it.dark)
            }
            "tradingOverAccent" -> {
                theme.tradingOverAccentLight = createDynamicColor(it.light)
                theme.tradingOverAccentDark = createDynamicColor(it.dark)
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
