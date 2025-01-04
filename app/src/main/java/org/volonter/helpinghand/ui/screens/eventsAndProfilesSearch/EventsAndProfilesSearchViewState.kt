package org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch



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

data class UserViewState(
    val id: String,
    val imageLink: String,
    val name: String,
)
