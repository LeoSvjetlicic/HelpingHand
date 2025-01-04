package org.volonter.helpinghand.data.repository

import com.google.android.gms.maps.model.LatLng
import org.volonter.helpinghand.domain.repository.EventRepository
import org.volonter.helpinghand.domain.usecases.CreateNewEventUseCase
import org.volonter.helpinghand.domain.usecases.GetAllEventsForSearchUseCase
import org.volonter.helpinghand.domain.usecases.GetAllMarkersUseCase
import org.volonter.helpinghand.domain.usecases.GetEventByIdUseCase
import org.volonter.helpinghand.domain.usecases.GetSupportedCitiesUseCase
import org.volonter.helpinghand.domain.usecases.ToggleApplicationButtonUseCase
import org.volonter.helpinghand.ui.screens.addevent.AddEventViewState
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendarViewState
import org.volonter.helpinghand.ui.screens.eventdetails.EventDetailsViewState
import org.volonter.helpinghand.ui.screens.map.MarkerViewState
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val createNewEventUseCase: CreateNewEventUseCase,
    private val getSupportedCitiesUseCase: GetSupportedCitiesUseCase,
    private val getAllMarkers: GetAllMarkersUseCase,
    private val getEventByIdUseCase: GetEventByIdUseCase,
    private val toggleApplicationUseCase: ToggleApplicationButtonUseCase
    private val getAllEventsForSearchUseCase: GetAllEventsForSearchUseCase
) : EventRepository {
    override suspend fun createNewEvent(
        inputViewState: AddEventViewState,
        latLng: LatLng,
        calendarState: RangeCalendarViewState
    ): Boolean = createNewEventUseCase.invoke(inputViewState, latLng, calendarState)

    override suspend fun getAllEvents(): List<MarkerViewState> = getAllMarkers.invoke()

    override suspend fun getEventById(eventId: String): EventDetailsViewState =
        getEventByIdUseCase.invoke(eventId)

    override suspend fun getSupportedCities(): Map<String, LatLng> =
        getSupportedCitiesUseCase.invoke()

    override suspend fun toggleApplication(eventId: String) =
        toggleApplicationUseCase.invoke(eventId)

    override suspend fun getAllEventsForSearch() = getAllEventsForSearchUseCase.invoke()
}
