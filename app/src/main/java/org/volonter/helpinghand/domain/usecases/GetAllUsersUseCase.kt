package org.volonter.helpinghand.domain.usecases

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.volonter.helpinghand.ui.common.viewstates.UserViewState
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
                val description = document.getString("description") ?: ""
                val email = document.getString("email") ?: ""

                if(name != null && imageLink != null) {
                    UserViewState(
                        id = id,
                        name = name,
                        imageLink = imageLink,
                        description = description,
                        email = email
                    )
                } else null
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
