package org.volonter.helpinghand.ui.screens.addreview

import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.viewstates.InputFieldState
import org.volonter.helpinghand.ui.common.viewstates.UserViewState

data class AddReviewViewState(
    val rating: Int = 0,
    val eventName: String = "Udruga Breza - Volonterska akcija",
    val title: InputFieldState = InputFieldState(label = R.string.topic),
    val body: InputFieldState = InputFieldState(label = R.string.body),
    val user: UserViewState = UserViewState(
        name = "Random lik",
        email = "random.lik@gmail.com",
        imageLink = "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
        description = ""
    )
)
