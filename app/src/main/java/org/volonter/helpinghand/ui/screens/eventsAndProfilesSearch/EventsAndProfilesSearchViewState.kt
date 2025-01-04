package org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch

import org.volonter.helpinghand.ui.common.viewstates.UserViewState


sealed class EventsAndProfilesSearchViewState {
    data class EventsViewState(
        val events: List<EventViewState>
    ) : EventsAndProfilesSearchViewState()

    data class ProfilesViewState(
        val profiles: List<UserViewState>
    ) : EventsAndProfilesSearchViewState()
}

data class EventViewState(
    val id: String,
    val title: String,
    val description: String,
    val time: String = "",
)
