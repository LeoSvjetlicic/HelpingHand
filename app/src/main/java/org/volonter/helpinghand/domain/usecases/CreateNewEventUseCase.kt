package org.volonter.helpinghand.domain.usecases

import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.volonter.helpinghand.ui.screens.addevent.AddEventViewState
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.RangeCalendarViewState
import org.volonter.helpinghand.ui.screens.addevent.formatDateTime
import javax.inject.Inject

class CreateNewEventUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore,
) {
    @OptIn(ExperimentalMaterial3Api::class)
    suspend fun invoke(
        inputViewState: AddEventViewState,
        latLng: LatLng,
        calendarState: RangeCalendarViewState
    ): Boolean {
        val currentUser = firebaseAuth.currentUser ?: return false
        val organisationId = currentUser.uid

        val addressParts = inputViewState.addressViewState.value.split(",").map { it.trim() }
        val city = if (addressParts.size > 1) addressParts[1] else ""

        val eventDocument = mapOf(
            "address" to inputViewState.addressViewState.value,
            "city" to city,
            "description" to inputViewState.descriptionViewState.value,
            "imageLink" to inputViewState.imageLinkViewState.value,
            "name" to inputViewState.titleViewState.value,
            "numberOfNeededVolunteers" to (inputViewState.neededVolunteers.value.toIntOrNull()
                ?: 0),
            "organisationId" to organisationId,
            "latLng" to mapOf("latitude" to latLng.latitude, "longitude" to latLng.longitude),
            "time" to formatDateTime(
                calendarState.selectedRange.startDay,
                calendarState.selectedRange.endDay,
                inputViewState.time.hour,
                inputViewState.time.minute
            ),
        )

        return try {
            firebaseDb.collection("events").add(eventDocument).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}
