package com.warrenbrasil.flamingosis.app

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import com.warrenbrasil.flamingosis.resources.FlamingosisResources

 abstract class FlamingosisActivity: AppCompatActivity(), BaseCyaneaActivity {

    private val resources: FlamingosisResources by lazy {
        FlamingosisResources(this, super.getResources(), cyanea)
    }

    override fun getResources(): Resources = resources
}
