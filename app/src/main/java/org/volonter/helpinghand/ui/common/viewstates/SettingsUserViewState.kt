package org.volonter.helpinghand.ui.common.viewstates

data class SettingsUserViewState(
    val imageLink: String,
    val name: String,
    val email: String,
    val description: String,
    val isOrganization: Boolean
)
//novi viewstate zato sto mi je trebalo isOrganization a nije mi se dalo sve mijenjati
