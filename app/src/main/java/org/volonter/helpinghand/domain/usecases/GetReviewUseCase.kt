package org.volonter.helpinghand.domain.usecases

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.volonter.helpinghand.ui.common.viewstates.ReviewViewState
import javax.inject.Inject

class GetReviewUseCase @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
) {
    suspend fun invoke(reviewId: String): ReviewViewState? {
        return try {
            val documentSnapshot = firestore.collection("rating").document(reviewId).get().await()

            if (!documentSnapshot.exists()) return null

            val data = documentSnapshot.data ?: return null

            val content = data["content"] as? String ?: ""
            val creationDate = data["creationDate"] as? String ?: ""
            val rating = (data["start"] as? Long)?.toInt() ?: 0
            val title = data["title"] as? String ?: ""
            val userId = data["userId"] as? String ?: ""

            val user = getUserDetailsUseCase.invoke(userId)

            ReviewViewState(
                rating = rating,
                title = title,
                description = content,
                date = creationDate,
                user = user
            )
        } catch (e: Exception) {
            null
        }
    }
}
