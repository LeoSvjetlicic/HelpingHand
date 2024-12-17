package org.volonter.helpinghand.ui.common.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.ui.theme.PrimaryCoral

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier.defaultMinSize(minHeight = 1.dp, minWidth = 1.dp),
        onClick = onClick,
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 30.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryCoral,
            contentColor = Color.White
        ),
        content = content
    )
}
