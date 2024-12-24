package org.volonter.helpinghand.ui.screens.addevent.components.calendar

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.leosvjetlicic.calendarlibrary.ui.calendarday.singleday.BaseCalendarDayContent
import com.leosvjetlicic.calendarlibrary.ui.calendarday.singleday.BaseCalendarDayIndicator
import com.leosvjetlicic.calendarlibrary.ui.calendarday.singleday.CalendarDay
import com.leosvjetlicic.calendarlibrary.ui.calendarday.singleday.ICalendarDay
import org.volonter.helpinghand.ui.theme.GreenGray40
import org.volonter.helpinghand.ui.theme.PrimaryGreen
import java.time.LocalDate

@Composable
fun RowScope.RangeCalendarDay(
    day: ICalendarDay,
    modifier: Modifier = Modifier,
    onClick: (LocalDate) -> Unit,
    content: @Composable BoxScope.() -> Unit = {
        BaseCalendarDayContent(
            modifier = Modifier.align(Alignment.Center),
            viewState = day as RangeCalendarDayViewState,
            selectedBackgroundColor = GreenGray40,
            onClick = onClick,
            indicator = {
                BaseCalendarDayIndicator(indicatorColor = PrimaryGreen)
            }
        )
    }
) {
    CalendarDay(
        modifier = modifier
            .weight(1f)
            .then(
                if (day is RangeCalendarDayViewState && day.isInRange && day.isCurrentMonth && day.isSelected) {
                    Modifier.drawBehind {
                        drawRect(
                            color = GreenGray40.copy(0.5f),
                            topLeft = if (day.isFirstDayInRange) {
                                Offset(
                                    center.x,
                                    10f
                                )
                            } else {
                                Offset(
                                    0f,
                                    10f
                                )
                            },
                            size = Size(
                                (size.width / 2),
                                size.height - 20
                            )
                        )
                    }
                } else
                    if (day is RangeCalendarDayViewState && day.isInRange && day.isCurrentMonth) {
                        Modifier.drawBehind {
                            drawRect(
                                color = GreenGray40.copy(0.5f),
                                topLeft =
                                Offset(
                                    0f,
                                    10f
                                ),
                                size = Size(
                                    size.width,
                                    size.height - 20
                                )
                            )
                        }
                    } else {
                        Modifier
                    }
            ),
        viewState = day,
        content = {
            content()
        }
    )
}
