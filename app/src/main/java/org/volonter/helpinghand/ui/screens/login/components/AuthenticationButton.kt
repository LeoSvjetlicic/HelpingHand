package org.volonter.helpinghand.ui.screens.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.volonter.helpinghand.ui.theme.Gray20

@Composable
fun AuthenticationButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.6f) // Optional: Set button width relative to the screen
            .height(30.dp) // Button height
            .background(
                color = Color.White, // Background color
                shape = RoundedCornerShape(6.dp) // Rounded corners
            )
            .padding(horizontal = 8.dp), // Padding inside the box
        contentAlignment = Alignment.Center // Center the text
    ) {
        Text(
            text = text,
            color = Color.Black, // Text color
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
