package org.volonter.helpinghand.ui.screens.login

import android.content.Context
import android.widget.Toast
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

    fun onIsLoginChange(isLogin: Boolean) {
        viewState.value = viewState.value.copy(isLogin = !viewState.value.isLogin)
    }

    fun login() {
        viewModelScope.launch {
            repository.login(
                viewState.value.emailViewState.query,
                viewState.value.passwordViewState.query
            )
        }
    }


    fun register(context: Context) {
        viewModelScope.launch {
            val isSuccess = repository.register(
                viewState.value.nameViewState.query,
                viewState.value.emailViewState.query,
                viewState.value.passwordViewState.query,
                viewState.value.isOrganisation
            )

            // Update view state or trigger a Toast message based on success or failure
            if (isSuccess) {
                // Handle success (you can show a success Toast here)
                // For example:
                Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
            } else {
                // Handle failure (you can show an error Toast here)
                Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
