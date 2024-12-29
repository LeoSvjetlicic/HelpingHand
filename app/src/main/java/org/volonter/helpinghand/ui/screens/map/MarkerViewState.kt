package org.volonter.helpinghand.ui.screens.map

import com.google.maps.android.compose.MarkerState

data class MarkerViewState(
    val markerState: MarkerState,
    val name: String,
    val description: String
)
