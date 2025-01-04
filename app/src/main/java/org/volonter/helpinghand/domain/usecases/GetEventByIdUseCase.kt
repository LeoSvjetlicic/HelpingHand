package org.volonter.helpinghand.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.volonter.helpinghand.ui.screens.eventdetails.EventDetailsViewState
import org.volonter.helpinghand.ui.screens.eventdetails.FinishedEventDetailsViewState
import org.volonter.helpinghand.ui.screens.eventdetails.UnfinishedEventDetailsViewState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetEventByIdUseCase @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val getOrganisationDetailsUseCase: GetUserDetailsUseCase,
    private val getReviewUseCase: GetReviewUseCase,
) {
    suspend operator fun invoke(eventId: String): EventDetailsViewState {
        return try {
            val documentSnapshot = firestore.collection("events").document(eventId).get().await()

            val data = documentSnapshot.data
            val name = data?.get("name") as? String ?: ""
            val description = data?.get("description") as? String ?: ""
            val imageLink = data?.get("imageLink") as? String ?: ""
            val time = data?.get("time") as? String ?: ""
            val address = data?.get("address") as? String ?: ""
            val callingNumber = data?.get("callingNumber") as? String ?: ""
            val numberOfNeededVolunteers =
                (data?.get("numberOfNeededVolunteers") as? Long)?.toInt() ?: 0
            val applicationIds = (data?.get("applicationIds") as? List<String>) ?: emptyList()
            val organisationId = data?.get("organisationId") as? String ?: ""
            val reviewIds = (data?.get("ratings") as? List<String>) ?: emptyList()

            val today = Date()
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val lastDate = parseLastDate(time, dateFormat)

            if (lastDate != null && lastDate.after(today)) {
                UnfinishedEventDetailsViewState(
                    id = eventId,
                    organisation = getOrganisationDetailsUseCase.invoke(organisationId),
                    imageLink = imageLink,
                    title = name,
                    date = time,
                    callingNumber = callingNumber,
                    location = address,
                    description = description,
                    neededVolunteers = numberOfNeededVolunteers,
                    appliedVolunteers = applicationIds,
                    isUserApplied = applicationIds.contains(auth.currentUser?.uid)
                )
            } else {
                FinishedEventDetailsViewState(
                    id = eventId,
                    organisation = getOrganisationDetailsUseCase.invoke(organisationId),
                    imageLink = imageLink,
                    title = name,
                    date = time,
                    callingNumber = callingNumber,
                    location = address,
                    description = description,
                    allReviews = reviewIds.mapNotNull { getReviewUseCase.invoke(it) }
                )
            }
        } catch (e: Exception) {
            EventDetailsViewState.ERROR
        }
    }

    private fun parseLastDate(time: String, dateFormat: SimpleDateFormat): Date? {
        val dateRange = time.split(" - ")
        val lastDateStr = dateRange.lastOrNull()?.trim()
        return try {
            lastDateStr?.let { dateFormat.parse(it) }
        } catch (e: Exception) {
            null
        }
    }
}
