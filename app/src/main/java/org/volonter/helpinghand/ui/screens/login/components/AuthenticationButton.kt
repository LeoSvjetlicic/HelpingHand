package org.volonter.helpinghand.ui.screens.login.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.volonter.helpinghand.ui.common.components.SecondaryButton

@Composable
fun AuthenticationButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    SecondaryButton(onClick, modifier = modifier) {
        Text(
            text = text,
            color = Color.Black, // Text color
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
