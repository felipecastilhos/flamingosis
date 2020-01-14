package com.warrenbrasil.flamingosis

import android.annotation.SuppressLint
import android.app.Application
import android.content.res.Resources
import android.util.Log
import com.warrenbrasil.flamingosis.colors.ColorLoader
import com.warrenbrasil.flamingosis.colors.models.ColorPallet
import com.warrenbrasil.flamingosis.theme.ThemeModel

class Flamingosis {

    var themeModel: ThemeModel? = null
        private set

    private val colorLoader: ColorLoader by lazy { ColorLoader() }

    fun updateTheme(colorPallet: ColorPallet) {
        colorLoader.saveColors(app, colorPallet)
        themeModel = colorLoader.loadColorList(app)
    }

    companion object {
        @SuppressLint("StaticFieldLeak") // application context is safe
        internal lateinit var app: Application
        lateinit var res: Resources

        /**
         * Initialize Flamingosis. This should be done in the [application][Application] class.
         */
        @JvmStatic
        fun init(app: Application, res: Resources) {
            Companion.app = app
            Companion.res = res
        }

        @JvmStatic
        fun isInitialized(): Boolean {
            return try {
                app
                res
                true
            } catch (e: UninitializedPropertyAccessException) {
                false
            }
        }

        fun updateTheme(colorPallet: ColorPallet) {
            INSTANCE.updateTheme(colorPallet)
        }

        private object Holder {
            val INSTANCE: Flamingosis
                get() {
                    try {
                        return Flamingosis()
                    } catch (e: UninitializedPropertyAccessException) {
                        throw IllegalStateException("Flamingosis.init must be called before referencing the singleton instance")
                    }
                }
        }

        @JvmStatic
        val INSTANCE: Flamingosis by lazy { Holder.INSTANCE }


        /**
         * Turns on logging for the [Flamingosis] library
         */
        @JvmStatic
        var loggingEnabled = false

        @JvmStatic
        fun log(tag: String, msg: String, ex: Throwable? = null) {
            if (loggingEnabled) {
                Log.d(tag, msg, ex)
            }
        }
    }
}