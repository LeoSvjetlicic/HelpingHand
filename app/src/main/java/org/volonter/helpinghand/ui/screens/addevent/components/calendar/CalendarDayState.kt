package org.volonter.helpinghand.ui.screens.addevent.components.calendar

import com.leosvjetlicic.calendarlibrary.ui.calendarday.singleday.ICalendarDay
import java.time.LocalDate

data class RangeCalendarDayViewState(
    override val value: LocalDate,
    override val isSelected: Boolean,
    override val isCurrentMonth: Boolean,
    override val isToday: Boolean,
    val isInRange: Boolean,
    val isFirstDayInRange: Boolean
) : ICalendarDay
