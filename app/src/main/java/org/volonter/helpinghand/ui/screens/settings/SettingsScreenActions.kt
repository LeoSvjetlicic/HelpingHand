package org.volonter.helpinghand.ui.screens.settings

sealed class SettingsScreenActions

data class ChangeImageLink(val value: String) : SettingsScreenActions()
data class ChangeUsername(val value: String) : SettingsScreenActions()
data class ChangeDescription(val value: String) : SettingsScreenActions()
