package org.volonter.helpinghand.utlis

import android.content.Context
import android.content.SharedPreferences
import org.volonter.helpinghand.utlis.Constants.SharedPreferencesConstants.SHARED_PREFERENCES_INT

object SharedPreferencesHelper {
    private lateinit var sharedPreferences: SharedPreferences
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(Constants.SharedPreferencesConstants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun getIntFromSharedPrefs(): Int {
        return sharedPreferences.getInt(
            SHARED_PREFERENCES_INT,
            1
        )
    }

    fun saveIntPrefs(value: Int) {
        sharedPreferences.edit()?.putInt(SHARED_PREFERENCES_INT, value)?.apply()
    }

    fun saveBooleanToPrefs(tag: String, value: Boolean) {
        sharedPreferences.edit()?.putBoolean(tag, value)?.apply()
    }

    fun getBooleanFromSharedPrefs(tag: String): Boolean {
        return sharedPreferences.getBoolean(
            tag,
            false
        )
    }

    fun clearSharedPrefs() {
        sharedPreferences.edit()?.clear()?.apply()
    }
}
