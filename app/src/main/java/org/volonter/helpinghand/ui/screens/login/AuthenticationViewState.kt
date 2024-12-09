package org.volonter.helpinghand.ui.screens.login

data class AuthenticationViewState(
    val email: String = "",
    val password: String = "",
    val isOrganisation: Boolean = false,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isLogin: Boolean = true
)
