package com.warrenbrasil.flamingosis

import android.app.Application
import android.content.res.Resources
import com.warrenbrasil.flamingosis.resources.FlamingosisResources

open class FlamingosisApp: Application() {
    private val resources: FlamingosisResources by lazy {
        FlamingosisResources(this, super.getResources(), flamingosis)
    }

    open val flamingosis: Flamingosis by lazy { Flamingosis.INSTANCE }

    override fun onCreate() {
        super.onCreate()
        Flamingosis.init(this, super.getResources())
    }

    override fun getResources(): Resources {
        return if (Flamingosis.isInitialized()) resources else super.getResources()
    }
}