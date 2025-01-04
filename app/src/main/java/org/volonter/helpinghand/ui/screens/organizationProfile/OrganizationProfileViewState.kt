package org.volonter.helpinghand.ui.screens.organizationProfile

import org.volonter.helpinghand.ui.common.viewstates.EventViewState
import org.volonter.helpinghand.ui.common.viewstates.UserViewState

sealed class OrganizationProfileViewState(
    val id: String = "",
    val organisation: UserViewState = UserViewState(
        id = "",
        imageLink = "https://upload.wikimedia.org/wikipedia/commons/8/8d/Frog_on_palm_frond.jpg",
        name = "Udruga Breza",
        email = "udruga.breza@gmail.com",
        description = "Udruga Breza Osijek posvećena je pružanju podrške osobama s invaliditetom."
    ),
    val imageLink: String = "https://upload.wikimedia.org/wikipedia/commons/8/8d/Frog_on_palm_frond.jpg",
    val title: String = "UDRUGA BREZA",
    val date: String = "15.12.2001",
    val callingNumber: String = "031 272 818",
    val location: String = "Ul. Dobrise Cesarica 10, 31000, Osijek",
    val description: String = "Udruga Breza Osijek posvećena je pružanju podrške osobama s invaliditetom. Ova volonterska akcija omogućava svim zainteresiranim osobama da se uključe u različite aktivnosti.",
)


data class FinishedEventsViewState(
    val eventsPerPage: Int = 5,
    val currentPage: Int = 1,
    val allReviews: List<EventViewState> = listOf(
        EventViewState(
            "Breza",
            "Udruga Breza organizira događaj",
            5
        ),
        EventViewState(
            "Breza2",
            "Udruga Breza organizira događaj",
            3
        ),
        EventViewState(
            "Breza3",
            "Udruga Breza organizira događaj",
            4
        ),
        EventViewState(
            "Breza4",
            "Udruga Breza organizira događaj",
            5
        ),
        EventViewState(
            "Breza5",
            "Udruga Breza organizira događaj",
            5
        ),
        EventViewState(
            "Breza6",
            "Udruga Breza organizira događaj",
            5
        ),
    )
) : OrganizationProfileViewState()
