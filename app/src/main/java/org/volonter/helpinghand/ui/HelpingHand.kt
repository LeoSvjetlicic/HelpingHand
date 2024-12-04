package org.volonter.helpinghand.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.volonter.helpinghand.utlis.SharedPreferencesHelper
import org.volonter.helpinghand.utlis.StringResourcesProvider

@HiltAndroidApp
class HelpingHand : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesHelper.init(this)
        stringResourcesProvider = StringResourcesProvider(this)
    }

    companion object {
        lateinit var stringResourcesProvider: StringResourcesProvider
    }
}
