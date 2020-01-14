package com.warrenbrasil.flamingosis.app

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import com.warrenbrasil.flamingosis.resources.FlamingosisResources

abstract class FlamingosisActivity : AppCompatActivity(), BaseFlamingosisActivity {

    private val resources: FlamingosisResources by lazy {
        FlamingosisResources(this, super.getResources(), flamingosis)
    }

    override fun getResources(): Resources = resources
}
