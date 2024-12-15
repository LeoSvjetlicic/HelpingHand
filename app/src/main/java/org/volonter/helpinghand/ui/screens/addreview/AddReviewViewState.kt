package org.volonter.helpinghand.ui.screens.addreview

import org.volonter.helpinghand.ui.common.viewstates.UserViewState

data class AddReviewViewState(
    val rating: Int = 0,
    val eventName: String = "",
    val title: String = "",
    val description: String = "",
    val user: UserViewState = UserViewState(
        name = "Random lik",
        email = "random.lik@gmail.com",
        imageLink = "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg"
    )
)
