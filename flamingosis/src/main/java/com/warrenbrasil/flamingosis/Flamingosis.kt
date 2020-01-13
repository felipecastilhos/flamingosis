package com.warrenbrasil.flamingosis

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.util.Log
import androidx.annotation.MainThread
import com.warrenbrasil.flamingosis.colors.ColorLoader
import com.warrenbrasil.flamingosis.inflater.FlamingosisInflationDelegate
import com.warrenbrasil.flamingosis.inflater.FlamingosisLayoutInflater
import com.warrenbrasil.flamingosis.sharedpreferences.*
import com.warrenbrasil.flamingosis.theme.ThemeModel
import kotlin.properties.Delegates

class Flamingosis private constructor(private val prefs: SharedPreferences) {

    var themeModel by Delegates.notNull<ThemeModel>()
        private set

    private var colorLoader by Delegates.notNull<ColorLoader>()

    init {
        saveDynamicTheme()
        loadDefaults()
    }

    private fun loadDefaults() {
        themeModel = ThemeModel()
        colorLoader = ColorLoader()
        saveCurrentTheme()
        themeModel = colorLoader.loadColorList(prefs)
    }

    private fun saveDynamicTheme() {
        val palletJsonString = createColorPalletJsonString()
        prefs.saveColorPallet(palletJsonString)
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

    private fun saveCurrentTheme() {
        prefs.saveTheme(themeModel)
    }

    companion object {
        const val PREF_FILE_NAME = "FlamingoosisPrefs"

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

        private object Holder {
            val INSTANCE: Flamingosis
                get() {
                    try {
                        val preferences =
                            app.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
                        return Flamingosis(preferences)
                    } catch (e: UninitializedPropertyAccessException) {
                        throw IllegalStateException("Flamingosis.init must be called before referencing the singleton instance")
                    }
                }
        }

        @JvmStatic
        val INSTANCE: Flamingosis by lazy { Holder.INSTANCE }

        /**
         * Intercept and create views at inflation time
         *
         * @delegate The delegate used to intercept and create views
         */
        @JvmStatic
        @MainThread
        fun setInflationDelegate(delegate: FlamingosisInflationDelegate) {
            FlamingosisLayoutInflater.inflationDelegate = delegate
        }

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