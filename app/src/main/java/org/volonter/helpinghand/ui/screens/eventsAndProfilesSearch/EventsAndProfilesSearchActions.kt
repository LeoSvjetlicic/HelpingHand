package org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch

sealed class EventsAndProfilesSearchActions

data class OnEventUserClick(val email: String) : EventsAndProfilesSearchActions()
data class OnProfileUserClick(val email: String) : EventsAndProfilesSearchActions()
data object OnBackClick : EventsAndProfilesSearchActions()