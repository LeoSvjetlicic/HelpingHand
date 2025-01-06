package org.volonter.helpinghand.ui.screens.volunteerProfile

interface PaginatedViewState {
    val eventsPerPage: Int
    val currentPage: Int
    val events: List<EventViewState>
}

sealed class VolunteerProfileViewState(
    open val id: String,
    open val imageLink: String,
    open val title: String,
)

data class FinishedEventsViewState(
    override val eventsPerPage: Int = 5,
    override val currentPage: Int = 1,
    override var events: List<EventViewState> = emptyList(),
    override var id: String = "",
    override var imageLink: String = "https://upload.wikimedia.org/wikipedia/commons/8/8d/Frog_on_palm_frond.jpg",
    override var title: String = "",
) : VolunteerProfileViewState(
    id = id,
    imageLink = imageLink,
    title = title,
), PaginatedViewState

data class InProgressEventsViewState(
    override val eventsPerPage: Int = 5,
    override val currentPage: Int = 1,
    override var events: List<EventViewState> = emptyList(),
    override var id: String = "",
    override var imageLink: String = "https://upload.wikimedia.org/wikipedia/commons/8/8d/Frog_on_palm_frond.jpg",
    override var title: String = "",
) : VolunteerProfileViewState(
    id = id,
    imageLink = imageLink,
    title = title,
), PaginatedViewState

data class EventViewState(
    val id: String,
    val title: String,
    val description: String,
    val rating: Int = 0,
)