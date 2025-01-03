package org.volonter.helpinghand.domain.repository

import com.google.android.gms.maps.model.LatLng
import org.volonter.helpinghand.ui.screens.addevent.AddEventViewState
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendarViewState
import org.volonter.helpinghand.ui.screens.map.MarkerViewState

interface EventRepository {
    suspend fun createNewEvent(
        inputViewState: AddEventViewState,
        latLng: LatLng,
        calendarState: RangeCalendarViewState
    ): Boolean

    suspend fun getAllEvents(): List<MarkerViewState>
    suspend fun getSupportedCities(): Map<String, LatLng>
}
