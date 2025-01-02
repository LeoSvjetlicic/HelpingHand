package org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch

import org.volonter.helpinghand.ui.common.viewstates.EventViewState
import org.volonter.helpinghand.ui.common.viewstates.InProgressEventViewState
import org.volonter.helpinghand.ui.common.viewstates.UserViewState


sealed class EventsAndProfilesSearchViewState {

    data class EventsViewState(
        val events: List<InProgressEventViewState> = listOf(
            InProgressEventViewState(
                "Breza",
                "Udruga Breza organizira događaj",
                5,
                "12.12.2020",
                "12:00"
            ),
            InProgressEventViewState(
                "Breza2",
                "Udruga Breza organizira događaj",
                3,
                "12.12.2020",
                "12:00"
            ),
            InProgressEventViewState(
                "Breza3",
                "Udruga Breza organizira događaj",
                3,
                "12.12.2020",
                "12:00"
            ),
            InProgressEventViewState(
                "Breza4",
                "Udruga Breza organizira događaj",
                3,
                "12.12.2020",
                "12:00"
            ),
            InProgressEventViewState(
                "Breza5",
                "Udruga Breza organizira događaj",
                3,
                "12.12.2020",
                "12:00"
            ),
            InProgressEventViewState(
                "Breza6",
                "Udruga Breza organizira događaj",
                3,
                "12.12.2020",
                "12:00"
            )
        ),
        val isLoading: Boolean = false
    ) : EventsAndProfilesSearchViewState()

    data class ProfilesViewState(
        val profiles: List<UserViewState> = listOf(
            UserViewState(
                imageLink = "https://upload.wikimedia.org/wikipedia/commons/8/8d/Frog_on_palm_frond.jpg",
                name = "Udruga Breza",
                email = "udruga.breza@gmail.com",
                description = "Udruga Breza Osijek posvećena je pružanju podrške osobama s invaliditetom."
            ),
            UserViewState(
                imageLink = "https://upload.wikimedia.org/wikipedia/commons/8/8d/Frog_on_palm_frond.jpg",
                name = "Udruga Breza2",
                email = "udruga.breza@gmail.com",
                description = "Udruga Breza Osijek posvećena je pružanju podrške osobama s invaliditetom."
            ),
            UserViewState(
                imageLink = "https://upload.wikimedia.org/wikipedia/commons/8/8d/Frog_on_palm_frond.jpg",
                name = "Udruga Breza3",
                email = "udruga.breza@gmail.com",
                description = "Udruga Breza Osijek posvećena je pružanju podrške osobama s invaliditetom."
            )
        ),
        val isLoading: Boolean = false
    ) : EventsAndProfilesSearchViewState()
}
