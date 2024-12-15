package org.volonter.helpinghand.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.screens.login.components.LoginInputField

@Composable
fun LoginScreen(
    viewModel: AuthenticationViewModel,
    modifier: Modifier = Modifier
) {
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(interactionSource = interactionSource, indication = null) {
                focusManager.clearFocus()
            }) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .scale(1.2f),
            painter = painterResource(R.drawable.authentication_image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            TODO - already have an account....
            Text(text = stringResource(R.string.login))

            if (viewModel.viewState.value.isLogin) {
                LoginInputField(
                    viewState = viewModel.viewState.value.nameViewState,
                    onQueryChanged = viewModel::onNameChange
                )
            }
            Spacer(Modifier.height(4.dp))
            LoginInputField(
                viewState = viewModel.viewState.value.emailViewState,
                onQueryChanged = viewModel::onEmailChange
            )
            Spacer(Modifier.height(4.dp))
            LoginInputField(
                viewState = viewModel.viewState.value.passwordViewState,
                onQueryChanged = viewModel::onPasswordChange
            )
            Spacer(Modifier.height(4.dp))
            if (viewModel.viewState.value.isLogin) {
                LoginInputField(
                    viewState = viewModel.viewState.value.repeatPasswordViewState,
                    onQueryChanged = viewModel::onRepeatPasswordChange
                )
            }
            if (viewModel.viewState.value.isLogin) {
                //TODO - is organisation chackbox and button
            }
        }
    }
}
