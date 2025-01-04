package org.volonter.helpinghand.domain.repository

import org.volonter.helpinghand.ui.screens.addreview.AddReviewViewState

interface ReviewRepository {
    suspend fun addReview(
        eventId: String,
        inputViewState: AddReviewViewState,
    ): Boolean
}
