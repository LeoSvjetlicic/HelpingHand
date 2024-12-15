package org.volonter.helpinghand.ui.common.viewstates

data class ReviewViewState(
    val rating: Int,
    val title: String,
    val description: String,
    val date: String,
    val user: UserViewState
)
