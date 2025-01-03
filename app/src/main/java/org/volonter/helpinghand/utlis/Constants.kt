package org.volonter.helpinghand.utlis

object Constants {
    object SharedPreferencesConstants {
        const val SHARED_PREFERENCES_FILE_NAME = "sharedPreferences"

        const val SHARED_PREFERENCES_INT = "int"
        const val SHARED_PREFERENCES_STRING = "string"

        const val SHARED_PREFERENCES_IS_ORGANISATION = "stringIDUser"
    }

    object NavigationRoutes {
        const val LOGIN_ROUTE = "login"
        const val ADD_EVENT_ROUTE = "add event"
        const val EVENT_DETAILS_ROUTE = "event details"
        const val EVENT_ID = "eventId"
        const val EVENT_DETAILS_ROUTE_FULL = "$EVENT_DETAILS_ROUTE/{$EVENT_ID}"
        const val ADD_REVIEW_ROUTE = "add review"
        const val ORGANIZATION_PROFILE_ROUTE = "organization profile"
        const val VOLUNTEER_PROFILE_ROUTE = "volunteer profile"
        const val EVENTS_AND_PROFILES_SEARCH_ROUTE = "events and profiles search"
        const val SETTINGS_ROUTE = "settings"
        const val MAP_ROUTE = "mapica"
    }
}
