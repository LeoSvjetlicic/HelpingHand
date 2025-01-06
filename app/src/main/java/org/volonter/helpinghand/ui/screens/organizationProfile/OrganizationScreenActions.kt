package org.volonter.helpinghand.ui.screens.organizationProfile

sealed class OrganizationScreenActions

data object OnToggleApplicationToEventClick : OrganizationScreenActions()
data object OnPaginationRightClick : OrganizationScreenActions()
data object OnPaginationLeftClick : OrganizationScreenActions()
