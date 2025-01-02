package org.volonter.helpinghand.ui.screens.map

import com.google.maps.android.compose.MarkerState

data class MarkerViewState(
    val markerState: MarkerState,
    val id: String,
    val name: String,
    val description: String
)
