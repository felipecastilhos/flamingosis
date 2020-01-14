package com.warrenbrasil.flamingosis.resources

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Build
import com.warrenbrasil.flamingosis.Flamingosis
import com.warrenbrasil.flamingosis.R
import com.warrenbrasil.flamingosis.colors.models.DynamicColor

class FlamingosisResources (context: Context, original: Resources, private val flamingosis: Flamingosis = Flamingosis.INSTANCE) :
Resources(original.assets, original.displayMetrics, original.configuration) {
    private val ctx = context

    @Throws(NotFoundException::class)
    override fun getColor(id: Int): Int {
        return this.getColor(id, null)
    }

    @SuppressLint("PrivateResource")
    @Throws(NotFoundException::class)
    override fun getColor(id: Int, theme: Theme?): Int = when (id) {
        R.color.accountAccentLight -> solveColor(flamingosis.themeModel?.accountAccentLight, id, theme)
        R.color.accountAccentDark-> solveColor(flamingosis.themeModel?.accountAccentDark, id, theme)
        R.color.accountOverAccentLight-> solveColor(flamingosis.themeModel?.accountAccentDark, id, theme)
        R.color.accountOverAccentLight-> solveColor(flamingosis.themeModel?.accountAccentLight, id, theme)
        R.color.accountOverAccentDark-> solveColor(flamingosis.themeModel?.accountOverAccentDark, id, theme)
        R.color.cashAccentLight-> solveColor(flamingosis.themeModel?.cashAccentLight, id, theme)
        R.color.cashAccentDark-> solveColor(flamingosis.themeModel?.cashAccentDark, id, theme)
        R.color.cashOverAccentLight-> solveColor(flamingosis.themeModel?.cashOverAccentLight, id, theme)
        R.color.cashOverAccentDark -> solveColor(flamingosis.themeModel?.cashOverAccentDark, id, theme)
        R.color.wealthAccentLight -> solveColor(flamingosis.themeModel?.wealthAccentLight, id, theme)
        R.color.wealthAccentDark-> solveColor(flamingosis.themeModel?.wealthAccentDark, id, theme)
        R.color.wealthOverAccentLight -> solveColor(flamingosis.themeModel?.wealthOverAccentLight, id, theme)
        R.color.wealthOverAccentDark -> solveColor(flamingosis.themeModel?.wealthOverAccentDark, id, theme)
        R.color.tradingAccentLight-> solveColor(flamingosis.themeModel?.tradingAccentLight, id, theme)
        R.color.tradingAccentDark-> solveColor(flamingosis.themeModel?.tradingAccentDark, id, theme)
        R.color.tradingOverAccentLight-> solveColor(flamingosis.themeModel?.tradingOverAccentLight, id, theme)
        R.color.tradingOverAccentDark-> solveColor(flamingosis.themeModel?.tradingOverAccentDark, id, theme)
        else -> {
            getDefaultColorCodeCompat(id, theme)
        }
    }

    private fun solveColor(color: DynamicColor?, id: Int, theme: Theme?): Int {
        return color?.getColorCode(ctx) ?: getDefaultColorCodeCompat(id, theme)
    }

    private fun getDefaultColorCodeCompat(id: Int, theme: Theme?): Int {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            super.getColor(id) else super.getColor(id, theme)
    }

}   