package org.volonter.helpinghand.ui.screens.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.volonter.helpinghand.ui.theme.Gray40

@Composable
fun LoginInputField(
    viewState: LoginInputViewState,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    onQueryChanged: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(viewState.label),
            fontSize = 12.sp,
            color = if (viewState.errorMessage.isNotEmpty()) Color.Red
            else Color.Black
        )
        Spacer(Modifier.height(4.dp))
        BasicTextField(
            modifier = Modifier
                .padding(0.dp)
                .widthIn(max = 200.dp)
                .fillMaxWidth(),
            value = viewState.query,
            onValueChange = onQueryChanged,
            singleLine = true,
            textStyle = TextStyle.Default.copy(
                fontSize = 18.sp,
                lineHeight = 20.sp
            ),
            visualTransformation = if (isPassword) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
        )
        Spacer(Modifier.height(4.dp))
        Spacer(
            Modifier
                .height(1.dp)
                .widthIn(max = 200.dp)
                .fillMaxWidth()
                .background(Gray40)
        )
        Spacer(Modifier.height(4.dp))
        Text(text = viewState.errorMessage, fontSize = 12.sp, color = Color.Red)
    }
}
