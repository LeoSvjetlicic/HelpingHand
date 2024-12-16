package org.volonter.helpinghand.ui.screens.addreview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.theme.Gray30

@Composable
fun BackgroundTextFieldWithLabel(
    label: String,
    query: String,
    isError: Boolean,
    maxLines: Int,
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            color = Color.White,
        )
        Spacer(Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .background(Gray30),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f),
                value = query,
                maxLines = maxLines,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Gray30,
                    unfocusedContainerColor = Gray30,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = DarkGray
                ),
                textStyle = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    color = DarkGray
                ),
                placeholder = {
                    if (query.isEmpty()) {
                        Text(
                            text = label,
                            color = DarkGray,
                            fontSize = 16.sp
                        )
                    }
                },
                trailingIcon = {
                    if (isError) {
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
