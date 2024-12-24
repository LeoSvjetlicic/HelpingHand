package org.volonter.helpinghand.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.viewstates.InputFieldState

@Composable
fun BackgroundTextFieldWithLabel(
    viewState: InputFieldState,
    maxLines: Int,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    minRowHeight: Dp? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    onQueryChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(viewState.label),
            color = White,
        )
        Spacer(Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .then(
                    if (minRowHeight != null) {
                        Modifier.height(minRowHeight)
                    } else {
                        Modifier
                    }
                )
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(White),
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f),
                value = viewState.value,
                readOnly = readOnly,
                maxLines = maxLines,
                keyboardOptions = keyboardOptions,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = DarkGray
                ),
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    color = DarkGray
                ),
                placeholder = {
                    if (viewState.value.isEmpty()) {
                        Text(
                            text = stringResource(viewState.label),
                            color = DarkGray,
                            fontSize = 16.sp
                        )
                    }
                },
                trailingIcon = {
                    if (viewState.error.isNotEmpty()) {
                        Spacer(Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(R.drawable.ic_error),
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                },
                onValueChange = onQueryChange,
            )
        }
    }
}
