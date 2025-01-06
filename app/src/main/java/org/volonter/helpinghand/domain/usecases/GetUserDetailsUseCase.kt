package org.volonter.helpinghand.domain.usecases

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.volonter.helpinghand.ui.common.viewstates.UserViewState
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val firestore: FirebaseFirestore,
) {
    suspend fun invoke(organisationId: String): UserViewState {
        return try {
            val documentSnapshot =
                firestore.collection("users").document(organisationId).get().await()

            val data = documentSnapshot.data

            UserViewState(
                id = documentSnapshot.id,
                description = data?.get("description") as? String ?: "No description available",
                email = data?.get("email") as? String ?: "-",
                imageLink = data?.get("imageLink") as? String
                    ?: "https://upload.wikimedia.org/wikipedia/en/b/b1/Portrait_placeholder.png",
                name = data?.get("name") as? String ?: "-",
                isOrganisation = data?.get("isOrganisation") as? Boolean ?: false
            )
        } catch (e: Exception) {
            UserViewState(
                id = "error",
                description = "No description available",
                email = "-",
                imageLink = "https://upload.wikimedia.org/wikipedia/en/b/b1/Portrait_placeholder.png",
                name = "-",
                isOrganisation = false
            )
        }
    }
}
