package org.volonter.helpinghand.ui.screens.login.components

import androidx.annotation.StringRes

data class LoginInputViewState(
    @StringRes val label: Int,
    val errorMessage: String = "",
    val query: String = "",
)
