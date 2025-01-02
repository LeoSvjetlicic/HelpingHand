package org.volonter.helpinghand.ui.screens.settings

import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.viewstates.InputFieldState
import org.volonter.helpinghand.ui.common.viewstates.SettingsUserViewState
import org.volonter.helpinghand.ui.common.viewstates.UserViewState

data class SettingsViewState(
    val newUsername: InputFieldState = InputFieldState(label = R.string.new_username),
    val description: InputFieldState = InputFieldState(label = R.string.description),
    val user: SettingsUserViewState = SettingsUserViewState(
        name = "Random lik",
        email = "random.lik@gmail.com",
        imageLink = "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
        description = "",
        isOrganization = true
    )
)