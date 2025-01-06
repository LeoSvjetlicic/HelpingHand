package org.volonter.helpinghand.domain.usecases

import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.volonter.helpinghand.ui.screens.organizationProfile.EventViewState
import org.volonter.helpinghand.ui.screens.organizationProfile.FinishedEventsViewState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetFinishedEventsWithRatingsUseCase @Inject constructor(
    val firestore: FirebaseFirestore,
    val getUserDetailsUseCase: GetUserDetailsUseCase
) {
    suspend operator fun invoke(organisationId: String): FinishedEventsViewState? {
        return try {
            val organisationDetails = getUserDetailsUseCase.invoke(organisationId)
            val eventsSnapshot = firestore.collection("events")
                .whereEqualTo("organisationId", organisationId)
                .get()
                .await()

            val today = Date()
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val finishedEvents = eventsSnapshot.documents.mapNotNull { document ->

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
                            rating = averageRating,
                            reviewIds = reviewIds
                        )
                    } else null
                } else null
            }
            println("afadoifaiof ${organisationDetails.imageLink}")
            FinishedEventsViewState(
                id = organisationDetails.id,
                title = organisationDetails.name,
                description =organisationDetails.description,
                imageLink = organisationDetails.imageLink,
                allReviews = finishedEvents
            )
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
