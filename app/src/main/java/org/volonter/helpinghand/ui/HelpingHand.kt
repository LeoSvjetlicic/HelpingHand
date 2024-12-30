package org.volonter.helpinghand.ui

import android.app.Application
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.utlis.SharedPreferencesHelper
import org.volonter.helpinghand.utlis.StringResourcesProvider

@HiltAndroidApp
class HelpingHand : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesHelper.init(this)
        stringResourcesProvider = StringResourcesProvider(this)
        Places.initialize(
            applicationContext,
            stringResourcesProvider.getString(R.string.google_api_key)
        )
    }

    companion object {
        lateinit var stringResourcesProvider: StringResourcesProvider
    }
}
