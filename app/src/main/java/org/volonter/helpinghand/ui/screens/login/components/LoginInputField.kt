package org.volonter.helpinghand.ui.screens.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.volonter.helpinghand.ui.theme.InputFieldGray

@Composable
fun LoginInputField(
    viewState: LoginInputViewState,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = stringResource(viewState.label), fontSize = 16.sp)
        Spacer(Modifier.height(4.dp))
        TextField(
            modifier = Modifier
                .widthIn(max = 250.dp)
                .fillMaxWidth(),
            isError = viewState.errorMessage.isNotEmpty(),
            value = viewState.query,
            onValueChange = onQueryChanged,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = InputFieldGray,
                errorIndicatorColor = Color.Red,
                disabledIndicatorColor = InputFieldGray,
                unfocusedIndicatorColor = InputFieldGray
            )
        )
        Spacer(Modifier.height(4.dp))
        Text(text = viewState.errorMessage, fontSize = 12.sp, color = Color.Red)
    }
}
