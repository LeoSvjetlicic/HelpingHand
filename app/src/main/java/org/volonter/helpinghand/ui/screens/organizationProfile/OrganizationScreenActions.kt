package org.volonter.helpinghand.ui.screens.organizationProfile

sealed class OrganizationScreenActions

data class OnEventUserClick(val email: String) : OrganizationScreenActions()
data object OnToggleApplicationToEventClick : OrganizationScreenActions()
data object OnBackClick : OrganizationScreenActions()
data object OnCallClick : OrganizationScreenActions()
data object OnLocationClick : OrganizationScreenActions()
data object OnPaginationRightClick : OrganizationScreenActions()
data object OnPaginationLeftClick : OrganizationScreenActions()
