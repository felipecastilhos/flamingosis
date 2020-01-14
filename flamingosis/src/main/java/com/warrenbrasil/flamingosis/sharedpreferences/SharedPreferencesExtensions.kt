package com.warrenbrasil.flamingosis.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.warrenbrasil.flamingosis.colors.models.ColorPallet
import com.warrenbrasil.flamingosis.fromJson
import com.warrenbrasil.flamingosis.theme.ThemeModel
import com.warrenbrasil.flamingosis.toJson

const val PREFS_NAME = "FlamingoosisPrefs"

fun Context.getPrefs(): SharedPreferences =
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = this.edit()
    operation(editor)
    editor.apply()
}

fun SharedPreferences.saveTheme(themeModel: ThemeModel) {
    val themeModelJson = themeModel.toJson()
    set("theme", themeModelJson)
}

fun SharedPreferences.getTheme(): ThemeModel? {
    val themeModelJson = get<String>("theme")
    return themeModelJson?.fromJson(ThemeModel::class.java)
}

fun SharedPreferences.saveColorPallet(palletJsonString: String) {
    set("pallet", palletJsonString)
}


fun SharedPreferences.getColorPallet(): ColorPallet? {
    val palletJson = get<String>("pallet")
    return palletJson?.fromJson(ColorPallet::class.java)
}


fun SharedPreferences.remove(key: String) {
    val editor = this.edit()
    editor.remove(key)
    editor.apply()
}

operator fun SharedPreferences.set(key: String, value: Any?) {
    when (value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit { it.putInt(key, value) }
        is Boolean -> edit { it.putBoolean(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Long -> edit { it.putLong(key, value) }
        null -> remove(key)
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
    if (!contains(key)) {
        return null
    }
    return when (T::class) {
        String::class -> getString(key, defaultValue as? String) as T?
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

fun SharedPreferences.containsAtLeastOne(vararg keys: String): Boolean {
    return keys.indexOfFirst { contains(it) } != -1
}
