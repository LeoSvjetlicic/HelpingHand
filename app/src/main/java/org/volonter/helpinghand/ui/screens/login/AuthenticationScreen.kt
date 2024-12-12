package org.volonter.helpinghand.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.ui.screens.login.components.LoginInputField

@Composable
fun LoginScreen(
    viewModel: AuthenticationViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
//        Image()
        LoginInputField(
            viewState = viewModel.viewState.value.nameViewState,
            onQueryChanged = viewModel::onNameChange
        )
        Spacer(Modifier.height(16.dp))
        LoginInputField(
            viewState = viewModel.viewState.value.emailViewState,
            onQueryChanged = viewModel::onEmailChange
        )
        Spacer(Modifier.height(16.dp))
        LoginInputField(
            viewState = viewModel.viewState.value.passwordViewState,
            onQueryChanged = viewModel::onPasswordChange
        )
        Spacer(Modifier.height(16.dp))
        LoginInputField(
            viewState = viewModel.viewState.value.repeatPasswordViewState,
            onQueryChanged = viewModel::onRepeatPasswordChange
        )
    }
}
