package com.warrenbrasil.flamingosis.resources

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Build
import com.warrenbrasil.flamingosis.Flamingosis
import com.warrenbrasil.flamingosis.R

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
        R.color.accountAccentLight -> flamingosis.themeModel.accountAccentLight?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.accountAccentDark-> flamingosis.themeModel.accountAccentDark?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.accountOverAccentLight-> flamingosis.themeModel.accountAccentDark?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.accountOverAccentLight-> flamingosis.themeModel.accountAccentLight?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.accountOverAccentDark-> flamingosis.themeModel.accountOverAccentDark?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.cashAccentLight-> flamingosis.themeModel.cashAccentLight?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.cashAccentDark-> flamingosis.themeModel.cashAccentDark?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.cashOverAccentLight-> flamingosis.themeModel.cashOverAccentLight?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.cashOverAccentDark -> flamingosis.themeModel.cashOverAccentDark?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.wealthAccentLight -> flamingosis.themeModel.wealthAccentLight?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.wealthAccentDark-> flamingosis.themeModel.wealthAccentDark?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.wealthOverAccentLight -> flamingosis.themeModel.wealthOverAccentLight?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.wealthOverAccentDark -> flamingosis.themeModel.wealthOverAccentDark?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.tradingAccentLight-> flamingosis.themeModel.tradingAccentLight?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.tradingAccentDark-> flamingosis.themeModel.tradingAccentDark?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.tradingOverAccentLight-> flamingosis.themeModel.tradingOverAccentLight?.getColorCode(ctx) ?: throw NotFoundException()
        R.color.tradingOverAccentDark-> flamingosis.themeModel.tradingOverAccentDark?.getColorCode(ctx) ?: throw NotFoundException()
        else -> {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
                super.getColor(id) else super.getColor(id, theme)
        }
    }
}   