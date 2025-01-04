package org.volonter.helpinghand.domain.usecases

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.tasks.await
import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.UserViewState
import org.volonter.helpinghand.ui.screens.map.MarkerViewState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    val firestore: FirebaseFirestore
) {
    suspend operator fun invoke() : List<UserViewState> {
        return try {
            val snapshot = firestore.collection("users").get().await()

            snapshot.documents.mapNotNull { document ->
                val id = document.id
                val name = document.getString("name")
                val imageLink = document.getString("imageLink")

                if(name != null && imageLink != null) {
                    UserViewState(
                        id = id,
                        name = name,
                        imageLink = imageLink
                    )
                } else null
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}