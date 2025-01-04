package org.volonter.helpinghand.ui.screens.login

import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.screens.login.components.LoginInputViewState

data class AuthenticationViewState(
    val nameViewState: LoginInputViewState = LoginInputViewState(label = R.string.name),
    val emailViewState: LoginInputViewState = LoginInputViewState(label = R.string.email),
    val passwordViewState: LoginInputViewState = LoginInputViewState(label = R.string.password),
    val repeatPasswordViewState: LoginInputViewState = LoginInputViewState(label = R.string.repeat_password),
    val isOrganisation: Boolean = false,
    val isRegister: Boolean = false
)
