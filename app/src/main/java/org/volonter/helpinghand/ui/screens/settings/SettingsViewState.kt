package org.volonter.helpinghand.ui.screens.settings

import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.viewstates.InputFieldState
import org.volonter.helpinghand.ui.common.viewstates.SettingsUserViewState

data class SettingsViewState(
    val newImageLink: InputFieldState = InputFieldState(label = R.string.image_link),
    val newUsername: InputFieldState = InputFieldState(label = R.string.new_username),
    val description: InputFieldState = InputFieldState(label = R.string.description),
    val user: SettingsUserViewState = SettingsUserViewState(
        name = "Random lik",
        email = "random.lik@gmail.com",
        imageLink = "https://upload.wikimedia.org/wikipedia/en/b/b1/Portrait_placeholder.png",
        description = "",
        isOrganization = true
    )
)
