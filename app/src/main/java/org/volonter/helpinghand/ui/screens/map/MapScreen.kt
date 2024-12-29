package org.volonter.helpinghand.ui.screens.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    currentPosition: LatLng,
    markers: List<MarkerViewState>,
    modifier: Modifier = Modifier
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentPosition, 10f)
    }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
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
                }
            )
        }
    }
}
