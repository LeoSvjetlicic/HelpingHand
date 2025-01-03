package org.volonter.helpinghand.domain.usecases

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.tasks.await
import org.volonter.helpinghand.ui.screens.map.MarkerViewState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetAllMarkersUseCase @Inject constructor(val firestore: FirebaseFirestore) {
    suspend operator fun invoke(): List<MarkerViewState> {
        return try {
            val snapshot = firestore.collection("events").get().await()

            val today = Date()
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

            snapshot.documents.mapNotNull { document ->
                val id = document.id
                val name = document.getString("name")
                val description = document.getString("description")
                val time = document.getString("time")
                val latLngMap = document.get("latLng") as? Map<String, Double>
                val latLng = latLngMap?.let {
                    LatLng(it["latitude"] ?: 0.0, it["longitude"] ?: 0.0)
                }

                if (name != null && description != null && time != null && latLng != null) {
                    val isActive = isEventActive(time, today, dateFormat)
                    if (isActive) {
                        MarkerViewState(
                            markerState = MarkerState(position = latLng),
                            id = id,
                            name = name,
                            description = description
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
