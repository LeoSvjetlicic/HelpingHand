package org.volonter.helpinghand.ui.screens.login.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.theme.Gray20

@Composable
fun AuthenticationCheckbox(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            modifier = modifier,
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                uncheckedColor = Color.White,
                checkedColor = Gray20,
                checkmarkColor = Color.White
            ),
        )
        Text(text = stringResource(R.string.is_organisation), color = Color.White, fontSize = 12.sp)
    }
}
