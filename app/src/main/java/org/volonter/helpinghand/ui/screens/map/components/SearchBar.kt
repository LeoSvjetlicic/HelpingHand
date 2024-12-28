package org.volonter.helpinghand.ui.screens.map.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.R

@Composable
fun SearchBar(
    input: String,
    modifier: Modifier = Modifier,
    onInputChanged: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .padding(horizontal = 32.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        value = input,
        maxLines = 1,
        colors = TextFieldDefaults.colors().copy(
            focusedTextColor = Gray,
            unfocusedTextColor = Gray,
            disabledIndicatorColor = Transparent,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent
        ),
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, null, tint = Gray) },
        placeholder = { Text(text = stringResource(R.string.search)) },
        onValueChange = onInputChanged
    )
}

@Preview
@Composable
private fun af() {
    val a by remember { mutableStateOf("") }
    SearchBar(a) { }
}
