package org.volonter.helpinghand.domain.usecases

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.tasks.await
import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.EventViewState
import org.volonter.helpinghand.ui.screens.map.MarkerViewState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetAllEventsForSearchUseCase @Inject constructor(
    val firestore: FirebaseFirestore
) {
    suspend operator fun invoke(): List<EventViewState> {
        return try {
            val snapshot = firestore.collection("events").get().await()

            val today = Date()
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

            snapshot.documents.mapNotNull { document ->
                val id = document.id
                val name = document.getString("name")
                val description = document.getString("description")
                val time = document.getString("time")

                if (name != null && description != null && time != null) {
                    val isActive = isEventActive(time, today, dateFormat)
                    if (isActive) {
                        EventViewState(
                            id = id,
                            title = name,
                            description = description,
                            time = time
                        )
                    } else null
                } else null
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun isEventActive(time: String, today: Date, dateFormat: SimpleDateFormat): Boolean {
        val dateRange = time.split(" - ")
        val lastDateStr = dateRange.lastOrNull()

        return try {
            val lastDate = dateFormat.parse(lastDateStr)
            lastDate?.after(today) ?: false
        } catch (e: Exception) {
            false
        }
    }
}