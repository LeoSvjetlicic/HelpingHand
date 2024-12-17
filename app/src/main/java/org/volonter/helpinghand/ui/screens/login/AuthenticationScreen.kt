package org.volonter.helpinghand.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.screens.login.components.AuthenticationButton
import org.volonter.helpinghand.ui.screens.login.components.AuthenticationCheckbox
import org.volonter.helpinghand.ui.screens.login.components.LoginInputField

@Composable
fun LoginScreen(
    viewModel: AuthenticationViewModel,
    modifier: Modifier = Modifier
) {
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(interactionSource = interactionSource, indication = null) {
                focusManager.clearFocus()
            }
    ) {
        // Background Image
        Image(
            modifier = Modifier
                .fillMaxSize()
                .scale(1.2f),
            painter = painterResource(R.drawable.authentication_image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        // Content Centered in Screen
        Column(
            modifier = modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Conditional Account Text
            if (viewModel.viewState.value.isLogin) {
                AccountTextSection(
                    title = R.string.already_have_account,
                    action = R.string.sign_up_now,
                    onActionClick = {
                        viewModel.onIsLoginChange(viewModel.viewState.value.isLogin)
                    }
                )
            } else {
                AccountTextSection(
                    title = R.string.dont_have_account,
                    action = R.string.create_account,
                    onActionClick = {
                        viewModel.onIsLoginChange(viewModel.viewState.value.isLogin)
                    }
                )
            }

            Spacer(Modifier.height(24.dp))

            // Input Fields
            if (viewModel.viewState.value.isLogin) {
                LoginInputField(
                    viewState = viewModel.viewState.value.nameViewState,
                    onQueryChanged = viewModel::onNameChange
                )
                Spacer(Modifier.height(4.dp))
            }
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


        }

        // Fixed Button at Bottom
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // Fixes alignment to the bottom
                .padding(64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.viewState.value.isLogin) {
                AuthenticationCheckbox(
                    isChecked = viewModel.viewState.value.isOrganisation,
                    onCheckedChange = viewModel::onIsOrganizationChange
                )
            }
            Spacer(Modifier.height(24.dp))
            AuthenticationButton(
                text = if (viewModel.viewState.value.isLogin) stringResource(R.string.register) else stringResource(R.string.login),
                onClick = if (viewModel.viewState.value.isLogin) {
                    { viewModel.register(context) }
                } else {
                    viewModel::login
                }
            )
        }
    }
}

@Composable
fun AccountTextSection(title: Int, action: Int, onActionClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(start = 100.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(title),
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = stringResource(action),
            modifier = Modifier.clickable { onActionClick() },
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            textDecoration = TextDecoration.Underline
        )
    }
}
