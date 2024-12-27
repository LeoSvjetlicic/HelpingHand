package org.volonter.helpinghand.ui.screens.addevent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.components.BackgroundTextFieldWithLabel
import org.volonter.helpinghand.ui.common.components.PrimaryButton
import org.volonter.helpinghand.ui.screens.addevent.components.AddEventAction
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventAddressChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventDescriptionChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventPhoneNumberChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventTitleChange
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendar
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendarViewState
import org.volonter.helpinghand.ui.screens.addreview.components.ButtonPair
import org.volonter.helpinghand.ui.theme.Gray40
import org.volonter.helpinghand.ui.theme.GreenGray40
import org.volonter.helpinghand.ui.theme.PrimaryGreen
import org.volonter.helpinghand.ui.theme.SecondaryGreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventScreen(
    viewState: AddEventViewState,
    calendarViewState: RangeCalendarViewState,
    onPostClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
    onScreenAction: (AddEventAction) -> Unit,
) {
    var isCalendarVisible by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PrimaryGreen)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp), contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .fillParentMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 32.dp),
                        text = stringResource(viewState.topicText),
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        color = White
                    )
                }
            }
            item {
                Column(
                    Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(GreenGray40)
                        .padding(16.dp)
                ) {
                    BackgroundTextFieldWithLabel(
                        viewState = viewState.titleViewState,
                        maxLines = 1,
                        onQueryChange = { onScreenAction(OnEventTitleChange(it)) },
                    )
                    Spacer(Modifier.height(24.dp))
                    BackgroundTextFieldWithLabel(
                        viewState = viewState.addressViewState,
                        maxLines = 1,
                        onQueryChange = { onScreenAction(OnEventAddressChange(it)) },
                    )
                    Spacer(Modifier.height(24.dp))
                    Box {
                        BackgroundTextFieldWithLabel(
                            modifier = Modifier.padding(end = 40.dp),
                            viewState = viewState.timeViewState.copy(
                                value = formatDateTime(
                                    calendarViewState.selectedRange.startDay,
                                    calendarViewState.selectedRange.endDay,
                                    viewState.time.hour,
                                    viewState.time.minute

                                )
                            ),
                            maxLines = 1,
                            readOnly = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            onQueryChange = { },
                        )

                        Icon(
                            modifier = Modifier
                                .padding(top = 22.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    isCalendarVisible = true
                                }
                                .padding(6.dp)
                                .size(24.dp)
                                .align(Alignment.CenterEnd),
                            painter = painterResource(R.drawable.ic_calendar),
                            tint = White,
                            contentDescription = null
                        )
                    }
                    Spacer(Modifier.height(24.dp))
                    BackgroundTextFieldWithLabel(
                        viewState = viewState.contactNumberViewState,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        onQueryChange = { onScreenAction(OnEventPhoneNumberChange(it)) },
                    )
                    Spacer(Modifier.height(24.dp))
                    BackgroundTextFieldWithLabel(
                        minRowHeight = 100.dp,
                        viewState = viewState.descriptionViewState,
                        maxLines = Int.MAX_VALUE,
                        onQueryChange = { onScreenAction(OnEventDescriptionChange(it)) },
                    )
                }
            }
            item {
                ButtonPair(
                    primaryColor = GreenGray40,
                    onPostClick = {
                        onPostClick()
                    },
                    onCancelClick = onCancelClick
                )
            }
        }
        if (isCalendarVisible) {
            Box(
                modifier = Modifier
                    .background(Gray40.copy(alpha = 0.5f))
                    .fillMaxSize()
            )
        }
        if (isCalendarVisible) {
            var isTimePickerVisible by remember { mutableStateOf(false) }
            Popup(
                onDismissRequest = { isCalendarVisible = false },
                properties = PopupProperties(
                    focusable = true,
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                ),
                popupPositionProvider = object : PopupPositionProvider {
                    override fun calculatePosition(
                        anchorBounds: IntRect,
                        windowSize: IntSize,
                        layoutDirection: LayoutDirection,
                        popupContentSize: IntSize
                    ): IntOffset {
                        val x = (windowSize.width / 2) - (popupContentSize.width / 2)
                        val y = (windowSize.height / 2) - (popupContentSize.height / 2)
                        return IntOffset(x, y)
                    }
                }
            ) {
                if (isTimePickerVisible) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(White)
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TimePicker(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            colors = TimePickerDefaults.colors(
                                clockDialColor = PrimaryGreen,
                                clockDialSelectedContentColor = White,
                                containerColor = SecondaryGreen,
                                selectorColor = SecondaryGreen,
                                timeSelectorSelectedContainerColor = PrimaryGreen,
                                timeSelectorUnselectedContainerColor = PrimaryGreen,
                                timeSelectorSelectedContentColor = White,
                                timeSelectorUnselectedContentColor = White
                            ),
                            state = viewState.time,
                        )
                        PrimaryButton(PrimaryGreen,
                            { isTimePickerVisible = false }) {
                            Text(text = stringResource(R.string.close))
                        }
                    }
                } else {
                    RangeCalendar(
                        calendarViewState,
                        viewState.time,
                        { isTimePickerVisible = true },
                        Modifier,
                        onScreenAction
                    )
                }
            }
        }
    }
}


fun formatDateTime(
    startDate: LocalDate?,
    endDate: LocalDate?,
    hour: Int,
    minute: Int
): String {
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val formattedStartDate = startDate?.format(dateFormatter) ?: ""
    val formattedEndDate = endDate?.format(dateFormatter) ?: ""
    val formattedTime = String.format(Locale("hr", "HR"), "%02d:%02d", hour, minute)
    return when {
        startDate == null && endDate == null -> ""
        endDate == null || formattedStartDate == formattedEndDate -> {
            listOfNotNull(formattedStartDate, formattedTime)
                .joinToString(", ")
        }

        else -> {
            listOfNotNull("$formattedStartDate - $formattedEndDate", formattedTime)
                .joinToString(", ")
        }
    }
}
