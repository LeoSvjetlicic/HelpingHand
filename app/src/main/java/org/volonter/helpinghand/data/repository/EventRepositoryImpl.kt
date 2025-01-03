package org.volonter.helpinghand.data.repository

import com.google.android.gms.maps.model.LatLng
import org.volonter.helpinghand.domain.repository.EventRepository
import org.volonter.helpinghand.domain.usecases.CreateNewEventUseCase
import org.volonter.helpinghand.ui.screens.addevent.AddEventViewState
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendarViewState
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val createNewEventUseCase: CreateNewEventUseCase
) : EventRepository {
    override suspend fun createNewEvent(
        inputViewState: AddEventViewState,
        latLng: LatLng,
        calendarState: RangeCalendarViewState
    ): Boolean = createNewEventUseCase.invoke(inputViewState, latLng, calendarState)
}
