package org.volonter.helpinghand.domain.repository

import com.google.android.gms.maps.model.LatLng
import org.volonter.helpinghand.ui.screens.addevent.AddEventViewState
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendarViewState
import org.volonter.helpinghand.ui.screens.eventdetails.EventDetailsViewState
import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.EventViewState
import org.volonter.helpinghand.ui.screens.map.MarkerViewState

interface EventRepository {
    suspend fun createNewEvent(
        inputViewState: AddEventViewState,
        latLng: LatLng,
        calendarState: RangeCalendarViewState
    ): Boolean

    suspend fun getAllEvents(): List<MarkerViewState>
    suspend fun getEventById(eventId: String): EventDetailsViewState
    suspend fun getSupportedCities(): Map<String, LatLng>
    suspend fun toggleApplication(eventId: String): Result<Unit>
    suspend fun getAllEventsForSearch(): List<EventViewState>
}
