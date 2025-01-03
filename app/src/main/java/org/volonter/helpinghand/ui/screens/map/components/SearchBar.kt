package org.volonter.helpinghand.ui.screens.map.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.google.android.gms.maps.model.LatLng
import org.volonter.helpinghand.R

@Composable
fun SearchBar(
    input: String,
    supportedPlaces: Map<String, LatLng>,
    onElementSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
    onInputChanged: (String) -> Unit
) {
    var isPopupVisible by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        TextField(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            value = input,
            maxLines = 1,
            colors = TextFieldDefaults.colors().copy(
                focusedTextColor = Black,
                unfocusedTextColor = Black,
                focusedContainerColor = White,
                focusedPlaceholderColor = Black,
                unfocusedPlaceholderColor = Black,
                unfocusedLeadingIconColor = Black,
                focusedLeadingIconColor = Black,
                unfocusedContainerColor = White,
                disabledIndicatorColor = Transparent,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent
            ),
            leadingIcon = { Icon(imageVector = Icons.Filled.Search, null, tint = Gray) },
            placeholder = { Text(text = stringResource(R.string.search)) },
            onValueChange = {
                onInputChanged(it)
                isPopupVisible = true
            }
        )
        if (isPopupVisible) {
            Popup(onDismissRequest = { isPopupVisible = false }) {
                LazyColumn(
                    Modifier
                        .padding(top = 60.dp, start = 12.dp, end = 56.dp)
                        .fillMaxWidth()
                        .heightIn(max = 250.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(White)
                ) {
                    items(supportedPlaces.filter { it.key.startsWith(input, true) }
                        .map { Pair(it.key, it.value) }) {
                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .clickable {
                                    onElementSelect(it.first)
                                    isPopupVisible = false
                                },
                            text = it.first,
                            color = Black
                        )
                    }
                }
            }
        }
    }
}
