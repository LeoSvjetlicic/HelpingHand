package org.volonter.helpinghand.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ToggleApplicationButtonUseCase @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun invoke(eventId: String): Result<Unit> {
        val currentUserId = auth.currentUser?.uid ?: return Result.failure(Exception("User not logged in"))

        return try {
            val eventRef = firestore.collection("events").document(eventId)
            firestore.runTransaction { transaction ->
                val snapshot = transaction.get(eventRef)

                val applicantIds = (snapshot.get("applicationIds") as? MutableList<String>) ?: mutableListOf()

                if (applicantIds.contains(currentUserId)) {
                    applicantIds.remove(currentUserId)
                } else {
                    applicantIds.add(currentUserId)
                }

                transaction.update(eventRef, "applicationIds", applicantIds)
            }.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
