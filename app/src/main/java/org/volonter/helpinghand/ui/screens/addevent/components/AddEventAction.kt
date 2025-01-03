package org.volonter.helpinghand.ui.screens.addevent.components

import com.leosvjetlicic.calendarlibrary.ui.calendarheader.CalendarHeaderAction
import org.volonter.helpinghand.ui.screens.addevent.PlaceViewState
import java.time.LocalDate

sealed interface AddEventAction

data class OnEventTitleChange(val value: String) : AddEventAction
data class OnEventImageLinkChange(val value: String) : AddEventAction
data class OnEventAddressChange(val value: String) : AddEventAction
data class OnEventAddressSelect(val value: PlaceViewState) : AddEventAction
data class OnEventPhoneNumberChange(val value: String) : AddEventAction
data class OnEventVolunteersNumberChange(val value: String) : AddEventAction
data class OnEventCalendarChange(val value: CalendarAction) : AddEventAction
data class OnEventDescriptionChange(val value: String) : AddEventAction

sealed interface CalendarAction

data class OnEventHeaderChange(val value: CalendarHeaderAction) : CalendarAction
data class OnEventDayChange(val value: LocalDate) : CalendarAction
