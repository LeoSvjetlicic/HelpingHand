package org.volonter.helpinghand.ui.screens.addevent

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.components.BackgroundTextFieldWithLabel
import org.volonter.helpinghand.ui.common.components.PrimaryButton
import org.volonter.helpinghand.ui.common.components.SecondaryButton
import org.volonter.helpinghand.ui.screens.addevent.components.AddEventAction
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventAddressChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventAddressSelect
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventDescriptionChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventPhoneNumberChange
import org.volonter.helpinghand.ui.screens.addevent.components.OnEventTitleChange
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendarViewState
import org.volonter.helpinghand.ui.theme.GreenGray40
import org.volonter.helpinghand.ui.theme.PrimaryGreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventScreen(
    context: Context,
    viewState: AddEventViewState,
    calendarViewState: RangeCalendarViewState,
    onPostClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
    onScreenAction: (AddEventAction) -> Unit,
) {
    var isCalendarVisible by remember { mutableStateOf(false) }
    var addressSuggestions by remember { mutableStateOf(emptyList<PlaceViewState>()) }
    var showDropdown by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PrimaryGreen)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillParentMaxWidth()
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
                        onQueryChange = { query ->
                            onScreenAction(OnEventAddressChange(query))
                            fetchAddressSuggestions(context, query) { suggestions ->
                                addressSuggestions = suggestions
                                showDropdown = suggestions.isNotEmpty()
                            }
                        }
                    )
                    if (showDropdown) {
                        DropdownMenu(
                            suggestions = addressSuggestions,
                            onSuggestionSelected = { selectedAddress ->
                                fetchLatLngForPlaceId(
                                    context,
                                    selectedAddress.placeId,
                                    {
                                        onScreenAction(
                                            OnEventAddressSelect(
                                                selectedAddress.copy(
                                                    latLang = it
                                                )
                                            )
                                        )
                                    },
                                    {}
                                )
                                showDropdown = false
                            }
                        )
                    }
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
                            maxLines = 2,
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
                Row {
                    SecondaryButton(
                        onClick = { onPostClick() }
                    ) {
                        Text(text = stringResource(R.string.post))
                    }
                    Spacer(Modifier.weight(1f))
                    PrimaryButton(
                        GreenGray40,
                        onClick = { onCancelClick() }
                    ) {
                        Text(text = stringResource(R.string.cancel))
                    }
                }
            }
        }
    }
}

data class PlaceViewState(
    val name: String,
    val placeId: String,
    val latLang: LatLng? = null
)

fun fetchAddressSuggestions(
    context: Context,
    query: String,
    callback: (List<PlaceViewState>) -> Unit
) {
    val placesClient = Places.createClient(context)
    val request = FindAutocompletePredictionsRequest.builder()
        .setQuery(query)
        .build()

    placesClient.findAutocompletePredictions(request)
        .addOnSuccessListener { response ->
            val suggestions = response.autocompletePredictions.map { prediction ->
                PlaceViewState(
                    name = prediction.getFullText(null).toString(), // Name to display
                    placeId = prediction.placeId
                )
            }
            callback(suggestions)
        }
        .addOnFailureListener {
            callback(emptyList())
        }
}

fun fetchLatLngForPlaceId(
    context: Context,
    placeId: String,
    onResult: (LatLng?) -> Unit,
    onError: (Exception) -> Unit
) {
    val placesClient = Places.createClient(context)
    val placeFields = listOf(Place.Field.LOCATION)
    val request = FetchPlaceRequest.builder(placeId, placeFields).build()

    placesClient.fetchPlace(request)
        .addOnSuccessListener { response ->
            val latLng = response.place.location
            onResult(latLng)
        }
        .addOnFailureListener { exception ->
            onError(exception)
        }
}

@Composable
fun DropdownMenu(
    suggestions: List<PlaceViewState>,
    onSuggestionSelected: (PlaceViewState) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 200.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(White)
    ) {
        items(suggestions) { suggestion ->
            Text(
                text = suggestion.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSuggestionSelected(suggestion) }
                    .padding(16.dp),
                fontSize = 16.sp,
                color = Black
            )
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
