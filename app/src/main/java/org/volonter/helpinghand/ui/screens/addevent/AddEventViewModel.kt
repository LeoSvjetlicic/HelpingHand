package org.volonter.helpinghand.ui.screens.addevent

import android.widget.Toast
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
import org.volonter.helpinghand.R
import org.volonter.helpinghand.domain.repository.EventRepository
import org.volonter.helpinghand.ui.common.viewstates.InputFieldState
import org.volonter.helpinghand.ui.screens.addevent.components.AddEventAction
import org.volonter.helpinghand.ui.screens.addevent.components.CalendarAction
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventAddressChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventAddressSelect
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventCalendarChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventDayChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventDescriptionChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventHeaderChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventImageLinkChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventPhoneNumberChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventTitleChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventVolunteersNumberChange
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.CalendarHelper
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendarDayViewState
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendarViewState
import org.volonter.helpinghand.utlis.StringResourcesProvider
import org.volonter.helpinghand.utlis.ToastHelper
import java.time.LocalDate
import java.time.Month
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val helper: CalendarHelper,
    private var selected: Selected = Selected.DayRange(null, null),
    private val toastHelper: ToastHelper,
    private val stringResourcesProvider: StringResourcesProvider
) : ViewModel() {
    val inputViewState = mutableStateOf(AddEventViewState.EMPTY)
    private val selectedLatLng = mutableStateOf<LatLng?>(null)

    val calendarViewState = mutableStateOf(helper.generateCalendarViewState(selected = selected))

    private val month = mutableStateOf(LocalDate.now().month)

    private val year = mutableIntStateOf(LocalDate.now().year)

    suspend fun onPostClick(): Boolean {
        if (validateInputs()) return false
        val result = eventRepository.createNewEvent(
            inputViewState.value,
            selectedLatLng.value ?: LatLng(0.0, 0.0),
            calendarViewState.value
        )
        if (result) {
            toastHelper.createToast(
                stringResourcesProvider.getString(R.string.success),
                Toast.LENGTH_SHORT
            )
        } else {
            toastHelper.createToast(
                stringResourcesProvider.getString(R.string.failed),
                Toast.LENGTH_SHORT
            )
        }
        return result
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun validateInputs(): Boolean {
        var isValid = true
        with(inputViewState.value) {
            fun validateField(inputField: InputFieldState): InputFieldState {
                return if (inputField.value.isBlank()) {
                    isValid = false
                    inputField.copy(error = stringResourcesProvider.getString(R.string.this_field_cant_be_empty))
                } else {
                    inputField.copy(error = "")
                }
            }

            inputViewState.value = inputViewState.value.copy(
                imageLinkViewState = validateField(imageLinkViewState),
                titleViewState = validateField(titleViewState),
                addressViewState = validateField(addressViewState),
                descriptionViewState = validateField(descriptionViewState),
                contactNumberViewState = validateField(contactNumberViewState),
                neededVolunteers = validateField(neededVolunteers),
                timeViewState = if (formatDateTime(
                        calendarViewState.value.selectedRange.startDay,
                        calendarViewState.value.selectedRange.endDay,
                        inputViewState.value.time.hour,
                        inputViewState.value.time.minute
                    ).isBlank()
                ) timeViewState.copy(
                    error = stringResourcesProvider.getString(R.string.this_field_cant_be_empty)
                ) else {
                    timeViewState.copy(error = "")
                }
            )
        }

        return isValid
    }

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

            is OnEventCalendarChange -> {
                onCalendarAction(action.value)
            }

            is OnEventVolunteersNumberChange -> {
                inputViewState.value = inputViewState.value.copy(
                    neededVolunteers = inputViewState.value.neededVolunteers.copy(
                        value = action.value
                    )
                )
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
