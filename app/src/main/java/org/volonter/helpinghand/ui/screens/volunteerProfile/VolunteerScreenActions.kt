package org.volonter.helpinghand.ui.screens.volunteerProfile

sealed class VolunteerScreenActions

data class OnEventUserClick(val email: String) : VolunteerScreenActions()
data object OnToggleApplicationToEventClick : VolunteerScreenActions()
data object OnBackClick : VolunteerScreenActions()
data object OnPaginationRightClick : VolunteerScreenActions()
data object OnPaginationLeftClick : VolunteerScreenActions()
