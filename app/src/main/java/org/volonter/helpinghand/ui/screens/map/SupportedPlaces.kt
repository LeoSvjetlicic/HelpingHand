package org.volonter.helpinghand.ui.screens.map

import com.google.android.gms.maps.model.LatLng

val supportedPlaces = mapOf(
    "Pakrac" to LatLng(45.43639000, 17.18889000),
    "Zagreb" to LatLng(45.8008, 15.99),
    "Osijek" to LatLng(45.560684, 18.695853),
    "Sibinj" to LatLng(45.192, 17.908),
    "Grad X" to LatLng(45.30833000, 18.41056000),
    "Lipik" to LatLng(45.4142812, 17.1611921),
).toSortedMap().toMap()
