package org.volonter.helpinghand.domain.repository

import com.google.android.gms.maps.model.LatLng
import org.volonter.helpinghand.ui.screens.addevent.AddEventViewState
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendarViewState
import org.volonter.helpinghand.ui.screens.eventdetails.EventDetailsViewState
import org.volonter.helpinghand.ui.screens.map.MarkerViewState
import org.volonter.helpinghand.ui.screens.organizationProfile.FinishedEventsViewState
import org.volonter.helpinghand.ui.screens.volunteerProfile.InProgressEventsViewState

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
    suspend fun getAllEventsForSearch(): List<org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.EventViewState>
    suspend fun getFinishedEventsWithRatings(organisationId: String): FinishedEventsViewState?
    suspend fun getEventsByVolunteerId(volunteerId: String): Pair<org.volonter.helpinghand.ui.screens.volunteerProfile.FinishedEventsViewState, InProgressEventsViewState>?
}
