package org.volonter.helpinghand.ui.screens.eventdetails

sealed class EventScreenActions

data class OnOrganisationClick(val email: String) : EventScreenActions()
data object OnToggleApplicationToEventClick : EventScreenActions()
data object OnPaginationRightClick : EventScreenActions()
data object OnPaginationLeftClick : EventScreenActions()
