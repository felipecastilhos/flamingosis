package com.warrenbrasil.flamingosis.app

import androidx.annotation.StyleRes
import com.warrenbrasil.flamingosis.Flamingosis

interface BaseCyaneaActivity {
    val cyanea: Flamingosis get() = Flamingosis.INSTANCE
    @StyleRes fun getThemeResId(): Int = 0
}