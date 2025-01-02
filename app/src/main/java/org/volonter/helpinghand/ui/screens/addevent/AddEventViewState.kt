package org.volonter.helpinghand.ui.screens.addevent

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.viewstates.InputFieldState

data class AddEventViewState @OptIn(ExperimentalMaterial3Api::class) constructor(
    @StringRes val topicText: Int,
    val imageLinkViewState: InputFieldState,
    val titleViewState: InputFieldState,
    val addressViewState: InputFieldState,
    val calendarViewState: InputFieldState,
    val descriptionViewState: InputFieldState,
    val contactNumberViewState: InputFieldState,
    val timeViewState: InputFieldState,
    val time: TimePickerState
) {
    companion object {
        @OptIn(ExperimentalMaterial3Api::class)
        val EMPTY = AddEventViewState(
            topicText = R.string.add_new_event,
            imageLinkViewState = InputFieldState(label = R.string.image_link),
            titleViewState = InputFieldState(label = R.string.title),
            addressViewState = InputFieldState(label = R.string.address),
            descriptionViewState = InputFieldState(label = R.string.description),
            contactNumberViewState = InputFieldState(label = R.string.contact_phone),
            calendarViewState = InputFieldState(label = R.string.contact_phone),
            timeViewState = InputFieldState(label = R.string.date_and_time),
            time = TimePickerState(0, 0, true)

        )
    }
}
