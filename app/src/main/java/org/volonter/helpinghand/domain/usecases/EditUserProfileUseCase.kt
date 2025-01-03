package org.volonter.helpinghand.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EditUserProfileUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore,
) {
    suspend fun invoke(imageLink: String, name: String, description: String): Boolean {
        val currentUser = firebaseAuth.currentUser ?: return false
        val userId = currentUser.uid

        val userDocument = mapOf(
            "imageLink" to imageLink,
            "name" to name,
            "description" to description
        )

        return try {
            firebaseDb.collection("users").document(userId).update(userDocument).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}