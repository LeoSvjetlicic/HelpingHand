package org.volonter.helpinghand.ui.screens.addevent.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leosvjetlicic.calendarlibrary.R
import com.leosvjetlicic.calendarlibrary.ui.calendar.Calendar
import com.leosvjetlicic.calendarlibrary.ui.calendarday.CalendarDays
import com.leosvjetlicic.calendarlibrary.ui.calendarday.CalendarDaysRow
import com.leosvjetlicic.calendarlibrary.ui.calendarheader.ContentAction
import com.leosvjetlicic.calendarlibrary.ui.calendarheader.FirstTrailingAction
import com.leosvjetlicic.calendarlibrary.ui.calendarheader.SecondLeadingAction
import com.leosvjetlicic.calendarlibrary.ui.calendarheader.basecontent.BaseActionButtonContent
import com.leosvjetlicic.calendarlibrary.ui.calendarheader.basecontent.BaseCalendarHeaderContent
import org.volonter.helpinghand.ui.common.components.PrimaryButton
import org.volonter.helpinghand.ui.screens.addevent.components.AddEventAction
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventCalendarChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventDayChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventHeaderChange
import org.volonter.helpinghand.ui.theme.PrimaryGreen
import org.volonter.helpinghand.ui.theme.SecondaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RangeCalendar(
    calendarViewState: RangeCalendarViewState,
    time: TimePickerState,
    onOpenTimePicker: () -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    onScreenAction: (AddEventAction) -> Unit
) {
    Calendar(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp),
        viewState = calendarViewState,
        onHeaderAction = { onScreenAction(OnEventCalendarChange(OnEventHeaderChange(it))) },
        header = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BaseCalendarHeaderContent(
                    viewState = calendarViewState.headerViewState,
                    onAction = {
                        onScreenAction(
                            OnEventCalendarChange(
                                OnEventHeaderChange(
                                    ContentAction
                                )
                            )
                        )
                    }
                )
                Row {
                    BaseActionButtonContent(
                        iconId = R.drawable.ic_left_single,
                        onClick = {
                            onScreenAction(
                                OnEventCalendarChange(
                                    OnEventHeaderChange(
                                        SecondLeadingAction
                                    )
                                )
                            )
                        }
                    )
                    BaseActionButtonContent(
                        iconId = R.drawable.ic_right_single,
                        onClick = {
                            onScreenAction(
                                OnEventCalendarChange(
                                    OnEventHeaderChange(
                                        FirstTrailingAction
                                    )
                                )
                            )
                        }
                    )
                }
            }
        },
        content = {
            Column {
                CalendarDays(
                    modifier = Modifier.fillMaxWidth(),
                    viewState = calendarViewState.daysViewState,
                    dayContent = { row ->
                        CalendarDaysRow(viewState = row) { day ->
                            RangeCalendarDay(
                                day = day,
                                onClick = {
                                    onScreenAction(OnEventCalendarChange(OnEventDayChange(it)))
                                }
                            )
                        }
                    }
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(org.volonter.helpinghand.R.string.time) + ":",
                        color = Color.Black,
                        fontSize = 24.sp
                    )
                    Text(
                        "${time.hour}:${time.minute}",
                        fontSize = 24.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(6.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                SecondaryGreen
                            )
                            .clickable { onOpenTimePicker() }
                            .padding(12.dp)
                    )
                }
                PrimaryButton(
                    PrimaryGreen,
                    { onCloseClick() },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                ) {
                    Text(text = stringResource(org.volonter.helpinghand.R.string.close))
                }
            }
        }
    )
}
