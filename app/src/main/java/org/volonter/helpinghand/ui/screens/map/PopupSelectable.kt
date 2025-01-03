package org.volonter.helpinghand.ui.screens.map


sealed interface PopupSelectable

data object MyProfileClick : PopupSelectable
data object SettingsClick : PopupSelectable
data object LogoutClick : PopupSelectable
