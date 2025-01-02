package org.volonter.helpinghand.ui.screens.addevent

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.leosvjetlicic.calendarlibrary.ui.calendarday.CalendarDaysViewState
import com.leosvjetlicic.calendarlibrary.ui.calendarheader.CalendarHeaderAction
import com.leosvjetlicic.calendarlibrary.ui.calendarheader.ContentAction
import com.leosvjetlicic.calendarlibrary.ui.calendarheader.FirstLeadingAction
import com.leosvjetlicic.calendarlibrary.ui.calendarheader.FirstTrailingAction
import com.leosvjetlicic.calendarlibrary.ui.calendarheader.SecondLeadingAction
import com.leosvjetlicic.calendarlibrary.ui.calendarheader.SecondTrailingAction
import com.leosvjetlicic.calendarlibrary.utils.DateHelper.getMiddleDate
import com.leosvjetlicic.calendarlibrary.utils.Selected
import dagger.hilt.android.lifecycle.HiltViewModel
import org.volonter.helpinghand.ui.screens.addevent.components.AddEventAction
import org.volonter.helpinghand.ui.screens.addevent.components.CalendarAction
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventAddressChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventAddressSelect
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventCalendarChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventCancelClick
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventDayChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventDescriptionChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventHeaderChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventImageLinkChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventPhoneNumberChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventPostClick
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventTitleChange
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.CalendarHelper
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendarDayViewState
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendarViewState
import java.time.LocalDate
import java.time.Month
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val helper: CalendarHelper,
    private var selected: Selected = Selected.DayRange(null, null),
) : ViewModel() {
    val inputViewState = mutableStateOf(AddEventViewState.EMPTY)
    val selectedLatLng = mutableStateOf<LatLng?>(null)
    val calendarViewState = mutableStateOf(helper.generateCalendarViewState(selected = selected))

    private val month = mutableStateOf(LocalDate.now().month)

    private val year = mutableIntStateOf(LocalDate.now().year)

    @OptIn(ExperimentalMaterial3Api::class)
    fun onScreenAction(action: AddEventAction) {
        when (action) {
            is OnEventImageLinkChange -> {
                inputViewState.value =
                    inputViewState.value.copy(
                        imageLinkViewState = inputViewState.value.imageLinkViewState.copy(
                            value = action.value
                        )
                    )
            }
            is OnEventTitleChange -> {
                inputViewState.value =
                    inputViewState.value.copy(
                        titleViewState = inputViewState.value.titleViewState.copy(
                            value = action.value
                        )
                    )
            }

            is OnEventAddressChange -> {
                inputViewState.value = inputViewState.value.copy(
                    addressViewState = inputViewState.value.addressViewState.copy(value = action.value)
                )
            }
            is OnEventAddressSelect -> {
                inputViewState.value = inputViewState.value.copy(
                    addressViewState = inputViewState.value.addressViewState.copy(value = action.value.name)
                )
                selectedLatLng.value = action.value.latLang
            }
            OnEventCancelClick -> {}
            is OnEventDescriptionChange -> {
                inputViewState.value = inputViewState.value.copy(
                    descriptionViewState = inputViewState.value.descriptionViewState.copy(
                        value = action.value
                    )
                )
            }

            is OnEventPhoneNumberChange -> {
                inputViewState.value = inputViewState.value.copy(
                    contactNumberViewState = inputViewState.value.contactNumberViewState.copy(
                        value = action.value
                    )
                )
            }

            OnEventPostClick -> {}
            is OnEventCalendarChange -> {
                onCalendarAction(action.value)
            }
        }
    }

    private fun onCalendarAction(action: CalendarAction) {
        when (action) {
            is OnEventDayChange -> {
                onDayClick(action.value)
            }

            is OnEventHeaderChange -> {
                onHeaderAction(action.value)
            }
        }
    }

    private fun onDayClick(clickedDay: LocalDate) {
        var newStartDate =
            (selected as Selected.DayRange).startDay
        var newEndDate =
            (selected as Selected.DayRange).endDay
        val selectedDaysSum =
            if (newEndDate != null && newStartDate != null) 2 else {
                if (newEndDate != null || newStartDate != null) 1 else 0
            }
        when (selectedDaysSum) {
            0 -> newStartDate = clickedDay
            1 -> {
                if (clickedDay == newStartDate) {
                    newStartDate = null
                } else if (newStartDate != null && clickedDay < newStartDate) {
                    newEndDate =
                        newStartDate
                    newStartDate = clickedDay
                } else if (clickedDay == newEndDate) {
                    newEndDate = null
                } else if (newEndDate != null && clickedDay > newEndDate) {
                    newStartDate =
                        newEndDate
                    newEndDate = clickedDay
                } else {
                    if (newStartDate == null) {
                        newStartDate = clickedDay
                    } else {
                        newEndDate = clickedDay
                    }
                }
            }

            2 -> {
                if (clickedDay != newStartDate && clickedDay != newEndDate) {
                    val middle = if (newStartDate != null && newEndDate != null) {
                        getMiddleDate(newStartDate, newEndDate)
                    } else {
                        null
                    }
                    if (clickedDay < middle) {
                        newStartDate = clickedDay
                    } else {
                        newEndDate = clickedDay
                    }
                } else {
                    if (newStartDate == clickedDay) {
                        newStartDate = null
                    } else if (newEndDate == clickedDay) {
                        newEndDate = null
                    }
                }
            }
        }

        val newDays = calendarViewState.value.daysViewState.days.map { week ->
            week.map { d ->
                d as RangeCalendarDayViewState
                when (d.value) {
                    newStartDate -> d.copy(
                        isSelected = true,
                        isInRange = newEndDate != null,
                        isFirstDayInRange = true
                    )

                    newEndDate -> d.copy(
                        isSelected = true,
                        isInRange = newStartDate != null,
                        isFirstDayInRange = false
                    )

                    else -> d.copy(
                        isSelected = false,
                        isInRange = newStartDate != null && newEndDate != null && d.value > newStartDate && d.value < newEndDate
                    )
                }
            }
        }
        val newDaysViewState = calendarViewState.value.daysViewState.copy(
            days = newDays,
        )
        selected = (selected as Selected.DayRange).copy(
            startDay = newStartDate,
            endDay = newEndDate
        )
        calendarViewState.value = copyViewState(
            calendarViewState.value,
            newDaysViewState,
            selected
        )
    }

    private fun onHeaderAction(action: CalendarHeaderAction) {
        when (action) {
            SecondLeadingAction -> {
                if (month.value.value == 1) {
                    month.value = Month.of(12)
                    year.intValue -= 1
                } else {
                    month.value = Month.of(month.value.value - 1)
                }
            }

            FirstTrailingAction -> {
                if (month.value.value == 12) {
                    month.value = Month.of(1)
                    year.intValue += 1
                } else {
                    month.value = Month.of(month.value.value + 1)
                }
            }

            FirstLeadingAction -> {
                year.intValue -= 1
            }

            SecondTrailingAction -> {
                year.intValue += 1
            }

            ContentAction -> {
                year.intValue = LocalDate.now().year
                month.value = LocalDate.now().month
            }
        }
        calendarViewState.value = helper.generateCalendarViewState(
            year = year.intValue,
            month = month.value,
            selected = selected
        )
    }

    private fun copyViewState(
        viewState: RangeCalendarViewState,
        newDaysViewState: CalendarDaysViewState,
        selected: Selected
    ): RangeCalendarViewState =
        viewState.copy(
            daysViewState = newDaysViewState,
            selectedRange = selected as Selected.DayRange
        )
}
