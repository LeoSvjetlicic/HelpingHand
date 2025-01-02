package org.volonter.helpinghand.ui.screens.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.screens.map.components.SearchBar
import org.volonter.helpinghand.ui.theme.GreenGray40

@Composable
fun MapScreen(
    currentPosition: LatLng,
    supportedPlaces: Map<String, LatLng>,
    searchInput: String,
    onSearchInputChange: (String) -> Unit,
    onEventClick: (String) -> Unit,
    markers: List<MarkerViewState>,
    modifier: Modifier = Modifier,
    onFindEventsClick: () -> Unit
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentPosition, 15f)
    }
    var isMenuVisible by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Column {
            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                cameraPositionState = cameraPositionState
            ) {
                for (marker in markers) {
                    Marker(
                        state = marker.markerState,
                        title = marker.name,
                        snippet = marker.description,
                        onClick = {
                            marker.markerState.showInfoWindow()
                            true
                        },
                        onInfoWindowClick = {
                            onEventClick(marker.id)
                        }
                    )
                }
            }

            Text(
                modifier = Modifier
                    .background(GreenGray40)
                    .fillMaxWidth()
                    .clickable { onFindEventsClick() }
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.find_events)
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.Filled.Menu,
                null,
                tint = Color.Transparent,
                modifier = Modifier
                    .padding(6.dp)
                    .size(32.dp)
            )
            SearchBar(
                modifier = Modifier.weight(1f),
                input = searchInput,
                onElementSelect = {
                    cameraPositionState.move(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition.fromLatLngZoom(
                                supportedPlaces[it]!!,
                                15f
                            )
                        )
                    )
                },
                onInputChanged = onSearchInputChange
            )
            Icon(
                Icons.Filled.Menu,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(6.dp)
                    .size(32.dp)
                    .clickable {
                        isMenuVisible = !isMenuVisible
                    }
            )
        }
        if (isMenuVisible) {
            Popup(
                alignment = Alignment.TopEnd,
                onDismissRequest = { isMenuVisible = !isMenuVisible }
            ) {
                AnimatedVisibility(isMenuVisible) {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 50.dp)
                            .background(Color.White)
                            .wrapContentWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        elevation = CardDefaults.elevatedCardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.widthIn(max = 120.dp)) {
                            Text(
                                text = stringResource(R.string.my_profile),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        isMenuVisible = false
                                    }
                                    .padding(16.dp),
                                color = Color.Black
                            )
                            Text(
                                text = stringResource(R.string.settings),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        isMenuVisible = false
                                    }
                                    .padding(16.dp),
                                color = Color.Black
                            )
                            Text(
                                text = stringResource(R.string.post),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        isMenuVisible = false
                                    }
                                    .padding(16.dp),
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
