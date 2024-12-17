package org.volonter.helpinghand.ui.screens.eventdetails

sealed class EventScreenActions

data class OnReviewUserClick(val email: String) : EventScreenActions()
data class OnOrganisationClick(val email: String) : EventScreenActions()
data object OnToggleApplicationToEventClick : EventScreenActions()
data object OnBackClick : EventScreenActions()
data object OnAddReviewClick : EventScreenActions()
data object OnCallClick : EventScreenActions()
data object OnLocationClick : EventScreenActions()
data object OnPaginationRightClick : EventScreenActions()
data object OnPaginationLeftClick : EventScreenActions()
