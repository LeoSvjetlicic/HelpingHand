package org.volonter.helpinghand.ui.screens.eventdetails

sealed class EventScreenActions

data object OnToggleApplicationToEventClick : EventScreenActions()
data object OnPaginationRightClick : EventScreenActions()
data object OnPaginationLeftClick : EventScreenActions()
