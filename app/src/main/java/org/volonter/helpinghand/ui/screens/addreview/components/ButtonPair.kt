package org.volonter.helpinghand.ui.screens.addreview.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.components.PrimaryButton
import org.volonter.helpinghand.ui.common.components.SecondaryButton

@Composable
fun ButtonPair(
    primaryColor: Color,
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onPostClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        SecondaryButton(
            onClick = onCancelClick
        ) {
            Text(text = stringResource(R.string.cancel), fontSize = 16.sp)
        }
        PrimaryButton(
            backgroundColor = primaryColor,
            onClick = { onPostClick() }
        ) {
            Text(text = stringResource(R.string.post), fontSize = 16.sp)
        }
    }
}
