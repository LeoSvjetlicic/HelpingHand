package org.volonter.helpinghand.data.repository

import org.volonter.helpinghand.domain.repository.ReviewRepository
import org.volonter.helpinghand.domain.usecases.AddReviewUseCase
import org.volonter.helpinghand.ui.screens.addreview.AddReviewViewState
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val addReviewUseCase: AddReviewUseCase
) : ReviewRepository {
    override suspend fun addReview(inputViewState: AddReviewViewState): Boolean = addReviewUseCase.invoke(inputViewState)
}