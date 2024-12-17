package org.volonter.helpinghand.ui.screens.addreview

sealed class AddReviewCardActions

data class ChangeRating(val value: Int) : AddReviewCardActions()
data class ChangeTitle(val value: String) : AddReviewCardActions()
data class ChangeBody(val value: String) : AddReviewCardActions()
