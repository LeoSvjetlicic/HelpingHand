package org.volonter.helpinghand.ui.screens.volunteerProfile

sealed class VolunteerScreenActions

data object OnPaginationRightClick : VolunteerScreenActions()
data object OnPaginationLeftClick : VolunteerScreenActions()
