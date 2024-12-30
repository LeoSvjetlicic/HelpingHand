package org.volonter.helpinghand.ui.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            input = searchInput, onElementSelect = {
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
    }
}
