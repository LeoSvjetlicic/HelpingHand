package org.volonter.helpinghand.ui.screens.organizationProfile


sealed class OrganizationProfileViewState(
    open val id: String,
    open val imageLink: String,
    open val title: String,
    open val description: String,
)

data class FinishedEventsViewState(
    val eventsPerPage: Int = 5,
    val currentPage: Int = 1,
    val allReviews: List<EventViewState>,
    override val id: String = "",
    override val imageLink: String = "",
    override val title: String = "",
    override val description: String = ""
) : OrganizationProfileViewState(
    id = id,
    imageLink = imageLink,
    title = title,
    description = description
)

data class EventViewState(
    val id: String,
    val title: String,
    val description: String,
    val rating: Int,
    val reviewIds: List<String>,
)