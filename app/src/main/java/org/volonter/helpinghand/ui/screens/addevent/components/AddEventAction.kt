package org.volonter.helpinghand.ui.screens.addevent.components

import com.leosvjetlicic.calendarlibrary.ui.calendarheader.CalendarHeaderAction
import java.time.LocalDate

sealed interface AddEventAction

data class OnEventTitleChange(val value: String) : AddEventAction
data class OnEventAddressChange(val value: String) : AddEventAction
data class OnEventPhoneNumberChange(val value: String) : AddEventAction
data class OnEventCalendarChange(val value: CalendarAction) : AddEventAction
data object OnEventCancelClick : AddEventAction
data object OnEventPostClick : AddEventAction
data class OnEventDescriptionChange(val value: String) : AddEventAction

sealed interface CalendarAction

data class OnEventHeaderChange(val value: CalendarHeaderAction) : CalendarAction
data class OnEventDayChange(val value: LocalDate) : CalendarAction
