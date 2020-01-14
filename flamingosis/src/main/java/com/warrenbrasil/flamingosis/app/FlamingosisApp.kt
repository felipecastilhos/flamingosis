package com.warrenbrasil.flamingosis.app

import android.app.Application
import android.content.res.Resources
import com.warrenbrasil.flamingosis.Flamingosis
import com.warrenbrasil.flamingosis.colors.models.ColorPallet
import com.warrenbrasil.flamingosis.extensions.fromJson
import com.warrenbrasil.flamingosis.resources.FlamingosisResources

open class FlamingosisApp : Application() {
    private val resources: FlamingosisResources by lazy {
        FlamingosisResources(this, super.getResources(), flamingosis)
    }

    open val flamingosis: Flamingosis by lazy { Flamingosis.INSTANCE }

    override fun onCreate() {
        super.onCreate()
        Flamingosis.init(this, super.getResources())
        Flamingosis.updateTheme(
            createColorPalletJsonString().fromJson(
                ColorPallet::class.java
            )
        )
    }

    private fun createColorPalletJsonString(): String {
        return "{ \"colors\" : [\n" +
                "        { \"colorName\": \"accountAccent\",\n" +
                "            \"light\": \"#ffff00\",\n" +
                "            \"dark\" : \"#AAFFAA\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"
    }

    override fun getResources(): Resources {
        return if (Flamingosis.isInitialized()) resources else super.getResources()
    }
}