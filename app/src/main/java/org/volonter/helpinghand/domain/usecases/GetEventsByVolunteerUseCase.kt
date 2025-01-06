package org.volonter.helpinghand.domain.usecases

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldPath
import kotlinx.coroutines.tasks.await
import org.volonter.helpinghand.ui.screens.volunteerProfile.EventViewState
import org.volonter.helpinghand.ui.screens.volunteerProfile.FinishedEventsViewState
import org.volonter.helpinghand.ui.screens.volunteerProfile.InProgressEventsViewState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetEventsByVolunteerUseCase @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val getUserDetailsUseCase: GetUserDetailsUseCase
) {
    suspend operator fun invoke(volunteerId: String): Pair<FinishedEventsViewState, InProgressEventsViewState>? {
        return try {
            val volunteerDetails = getUserDetailsUseCase.invoke(volunteerId)

            val eventsSnapshot = firestore.collection("events")
                .whereArrayContains("applicationIds", volunteerId)
                .get()
                .await()

            val today = Date()
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

            val allFinishedEvents = eventsSnapshot.documents.mapNotNull { document ->
                val id = document.id
                val name = document.getString("name")
                val description = document.getString("description")
                val time = document.getString("time")
                val reviewIds = document.get("ratings") as? List<String> ?: emptyList()

                if (name != null && description != null && time != null) {
                    val isFinished = isEventFinished(time, today, dateFormat)
                    if (isFinished) {
                        val averageRating = fetchAverageRating(reviewIds)
                        EventViewState(
                            id = id,
                            title = name,
                            description = description,
                            rating = averageRating
                        )
                    } else null
                } else null
            }

            val allInProgressEvents = eventsSnapshot.documents.mapNotNull { document ->
                val id = document.id
                val name = document.getString("name")
                val description = document.getString("description")
                val time = document.getString("time")

                if (name != null && description != null && time != null) {
                    val isFinished = isEventFinished(time, today, dateFormat)
                    if (!isFinished) {
                        EventViewState(
                            id = id,
                            title = name,
                            description = description,
                            rating = 0
                        )
                    } else null
                } else null
            }

            val finishedEventsViewState = FinishedEventsViewState(
                id = volunteerDetails.id,
                imageLink = volunteerDetails.imageLink,
                title = volunteerDetails.name,
                events = allFinishedEvents
            )
            println("finishedEventsViewState: $finishedEventsViewState")

            val inProgressEventsViewState = InProgressEventsViewState(
                id = volunteerDetails.id,
                imageLink = volunteerDetails.imageLink,
                title = volunteerDetails.name,
                events = allInProgressEvents
            )

            Pair(finishedEventsViewState, inProgressEventsViewState)
        } catch (e: Exception) {
            null
        }
    }

    private fun isEventFinished(time: String, today: Date, dateFormat: SimpleDateFormat): Boolean {
        val dateRange = time.split(" - ")
        val lastDateStr = dateRange.lastOrNull()

        return try {
            val lastDate = dateFormat.parse(lastDateStr)
            lastDate?.before(today) ?: false
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun fetchAverageRating(reviewIds: List<String>): Int {
        return try {
            val reviews = firestore.collection("rating")
                .whereIn(FieldPath.documentId(), reviewIds)
                .get()
                .await()

            val totalStars = reviews.documents.sumOf { it.getLong("stars")?.toInt() ?: 0 }
            val totalReviews = reviews.size()

            if (totalReviews > 0) totalStars / totalReviews else 0
        } catch (e: Exception) {
            0
        }
    }

}
