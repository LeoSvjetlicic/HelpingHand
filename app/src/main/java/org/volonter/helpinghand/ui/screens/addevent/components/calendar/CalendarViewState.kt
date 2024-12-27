package org.volonter.helpinghand.ui.screens.addevent.components.calendar

import com.leosvjetlicic.calendarlibrary.ui.calendar.ICalendarViewState
import com.leosvjetlicic.calendarlibrary.ui.calendarday.CalendarDaysViewState
import com.leosvjetlicic.calendarlibrary.ui.calendarheader.CalendarHeaderViewState
import com.leosvjetlicic.calendarlibrary.ui.calendarweekdays.CalendarWeekDaysViewState
import com.leosvjetlicic.calendarlibrary.utils.Selected

data class RangeCalendarViewState(
    override val headerViewState: CalendarHeaderViewState,
    override val weekDaysViewState: CalendarWeekDaysViewState,
    override val daysViewState: CalendarDaysViewState,
    val selectedRange: Selected.DayRange,
) : ICalendarViewState
