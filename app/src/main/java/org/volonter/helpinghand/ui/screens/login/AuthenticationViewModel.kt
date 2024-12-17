package org.volonter.helpinghand.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.volonter.helpinghand.domain.repository.AuthenticationRepository
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    val repository: AuthenticationRepository
) : ViewModel() {
    val viewState = mutableStateOf(AuthenticationViewState())

    fun onEmailChange(email: String) {
        viewState.value =
            viewState.value.copy(emailViewState = viewState.value.emailViewState.copy(query = email))
    }

    fun onNameChange(name: String) {
        viewState.value =
            viewState.value.copy(nameViewState = viewState.value.nameViewState.copy(query = name))
    }

    fun onRepeatPasswordChange(password: String) {
        viewState.value =
            viewState.value.copy(
                repeatPasswordViewState = viewState.value.repeatPasswordViewState.copy(
                    query = password
                )
            )
    }

    fun onPasswordChange(password: String) {
        viewState.value =
            viewState.value.copy(passwordViewState = viewState.value.passwordViewState.copy(query = password))
    }

    fun onIsOrganizationChange(isOrganisation: Boolean) {
        viewState.value = viewState.value.copy(isOrganisation = !viewState.value.isOrganisation)
    }

    fun login() {
        viewModelScope.launch {
            repository.login(
                viewState.value.emailViewState.query,
                viewState.value.passwordViewState.query
            )
        }
    }

    fun register() {
        viewModelScope.launch {
            repository.register(
                viewState.value.nameViewState.query,
                viewState.value.emailViewState.query,
                viewState.value.passwordViewState.query,
                viewState.value.isOrganisation
            )
        }
    }
}
