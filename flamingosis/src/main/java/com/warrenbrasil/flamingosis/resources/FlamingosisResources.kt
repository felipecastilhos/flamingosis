package com.warrenbrasil.flamingosis.resources

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Build
import com.warrenbrasil.flamingosis.Flamingosis
import com.warrenbrasil.flamingosis.R
import com.warrenbrasil.flamingosis.colors.models.DynamicColor

@Suppress("DEPRECATION")
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
        R.color.accountAccentLight -> flamingosis.themeModel?.accountAccentLight.solveColor(id, theme)
        R.color.accountAccentDark-> flamingosis.themeModel?.accountAccentDark.solveColor(id, theme)
        R.color.accountOverAccentLight-> flamingosis.themeModel?.accountAccentDark.solveColor(id, theme)
        R.color.accountOverAccentLight-> flamingosis.themeModel?.accountAccentLight.solveColor(id, theme)
        R.color.accountOverAccentDark-> flamingosis.themeModel?.accountOverAccentDark.solveColor(id, theme)
        R.color.cashAccentLight-> flamingosis.themeModel?.cashAccentLight.solveColor(id, theme)
        R.color.cashAccentDark-> flamingosis.themeModel?.cashAccentDark.solveColor(id, theme)
        R.color.cashOverAccentLight-> flamingosis.themeModel?.cashOverAccentLight.solveColor(id, theme)
        R.color.cashOverAccentDark -> flamingosis.themeModel?.cashOverAccentDark.solveColor(id, theme)
        R.color.wealthAccentLight -> flamingosis.themeModel?.wealthAccentLight.solveColor(id, theme)
        R.color.wealthAccentDark-> flamingosis.themeModel?.wealthAccentDark.solveColor(id, theme)
        R.color.wealthOverAccentLight -> flamingosis.themeModel?.wealthOverAccentLight.solveColor(id, theme)
        R.color.wealthOverAccentDark -> flamingosis.themeModel?.wealthOverAccentDark.solveColor(id, theme)
        R.color.tradingAccentLight-> flamingosis.themeModel?.tradingAccentLight.solveColor(id, theme)
        R.color.tradingAccentDark-> flamingosis.themeModel?.tradingAccentDark.solveColor(id, theme)
        R.color.tradingOverAccentLight-> flamingosis.themeModel?.tradingOverAccentLight.solveColor(id, theme)
        R.color.tradingOverAccentDark-> flamingosis.themeModel?.tradingOverAccentDark.solveColor(id, theme)
        else -> {
            getDefaultColorCodeCompat(id, theme)
        }
    }

    private fun DynamicColor?.solveColor(id: Int, theme: Theme?): Int {
        return this?.getColorCode(ctx) ?: getDefaultColorCodeCompat(id, theme)
    }

    private fun getDefaultColorCodeCompat(id: Int, theme: Theme?): Int {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            super.getColor(id) else super.getColor(id, theme)
    }

}   