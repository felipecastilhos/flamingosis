package com.warrenbrasil.flamingosis.app

import androidx.annotation.StyleRes
import com.warrenbrasil.flamingosis.Flamingosis

interface BaseFlamingosisActivity {
    val flamingosis: Flamingosis get() = Flamingosis.INSTANCE
    @StyleRes fun getThemeResId(): Int = 0
}