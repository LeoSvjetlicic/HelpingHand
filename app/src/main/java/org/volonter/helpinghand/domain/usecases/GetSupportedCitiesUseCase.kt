package org.volonter.helpinghand.domain.usecases

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetSupportedCitiesUseCase @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend operator fun invoke(): Map<String, LatLng> {
        return try {
            val snapshot = firestore.collection("events").get().await()

            val cityLatLngs = snapshot.documents.mapNotNull { document ->
                val city = document.getString("city")
                val latLngMap = document.get("latLng") as? Map<String, Double>
                val latLng = latLngMap?.let {
                    LatLng(it["latitude"] ?: 0.0, it["longitude"] ?: 0.0)
                }

                if (city != null && latLng != null) {
                    city to latLng
                } else {
                    null
                }
            }

            cityLatLngs.groupBy({ it.first }, { it.second })
                .mapValues { (_, latLngList) ->
                    calculateAverageLatLng(latLngList)
                }
        } catch (e: Exception) {
            emptyMap()
        }
    }

    private fun calculateAverageLatLng(latLngs: List<LatLng>): LatLng {
        val averageLatitude = latLngs.map { it.latitude }.average()
        val averageLongitude = latLngs.map { it.longitude }.average()
        return LatLng(averageLatitude, averageLongitude)
    }
}
