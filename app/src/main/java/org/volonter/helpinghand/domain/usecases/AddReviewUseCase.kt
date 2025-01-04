package org.volonter.helpinghand.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.volonter.helpinghand.ui.screens.addreview.AddReviewViewState
import java.time.LocalDate
import javax.inject.Inject

class AddReviewUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore,
) {
    suspend fun invoke(
        inputViewState: AddReviewViewState
    ): Boolean {
        val user = firebaseAuth.currentUser ?: return false
        val userId = user.uid

        val reviewDocument = mapOf(
            "userId" to userId,
            "stars" to inputViewState.rating,
            "content" to inputViewState.body.value,
            "creationDate" to LocalDate.now().toString(),
            "title" to inputViewState.title.value,
            //"eventId" to inputViewState.eventId
        )

        return try {
            firebaseDb.collection("rating").add(reviewDocument).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
