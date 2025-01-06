package org.volonter.helpinghand.ui.screens.eventdetails

import org.volonter.helpinghand.ui.common.viewstates.ReviewViewState
import org.volonter.helpinghand.ui.common.viewstates.UserViewState

open class EventDetailsViewState(
    open val id: String,
    open val organisation: UserViewState,
    open val imageLink: String,
    open val title: String,
    open val date: String,
    open val callingNumber: String,
    open val location: String,
    open val description: String,
) {
    companion object {
        val ERROR = EventDetailsViewState(
            "Error",
            UserViewState(
                "error",
                "https://upload.wikimedia.org/wikipedia/en/b/b1/Portrait_placeholder.png",
                "Error loading",
                "Error loading",
                "Error loading",
                false
            ),
            imageLink = "https://upload.wikimedia.org/wikipedia/en/b/b1/Portrait_placeholder.png",
            title = "Error loading",
            date = "Error loading",
            callingNumber = "Error loading",
            location = "Error loading",
            description = "Error loading"
        )
    }
}

data class FinishedEventDetailsViewState(
    override val id: String = "",
    override val organisation: UserViewState = UserViewState("", "", "", "", "", false),
    override val imageLink: String = "",
    override val title: String = "",
    override val date: String = "",
    override val callingNumber: String = "",
    override val location: String = "",
    override val description: String = "",
    val reviewsPerPage: Int = 5,
    val currentPage: Int = 1,
    val allReviews: List<ReviewViewState> = emptyList()
) : EventDetailsViewState(
    id,
    organisation,
    imageLink,
    title,
    date,
    callingNumber,
    location,
    description
)

data class UnfinishedEventDetailsViewState(
    override val id: String = "",
    override val organisation: UserViewState = UserViewState("", "", "", "", "", false),
    override val imageLink: String = "",
    override val title: String = "",
    override val date: String = "",
    override val callingNumber: String = "",
    override val location: String = "",
    override val description: String = "",
    val isUserApplied: Boolean = false,
    val neededVolunteers: Int = 12,
    val appliedVolunteers: List<String> = emptyList()
) : EventDetailsViewState(
    id,
    organisation,
    imageLink,
    title,
    date,
    callingNumber,
    location,
    description
)
