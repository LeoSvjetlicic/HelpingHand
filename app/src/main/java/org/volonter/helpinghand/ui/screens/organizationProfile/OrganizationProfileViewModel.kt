package org.volonter.helpinghand.ui.screens.organizationProfile

import org.volonter.helpinghand.ui.screens.organizationProfile.OrganizationScreenActions.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrganizationProfileViewModel @Inject constructor() : ViewModel() {
    var viewState: MutableState<OrganizationProfileViewState> =
        mutableStateOf(FinishedEventsViewState())

    fun onScreenAction(action: OrganizationScreenActions) = when (action) {
        is OnEventUserClick -> {}
        OnBackClick -> {}
        OnCallClick -> {}
        OnLocationClick -> {}
        OnPaginationLeftClick -> {
            viewState.value =
                (viewState.value as FinishedEventsViewState).copy(
                    currentPage =
                    (viewState.value as FinishedEventsViewState).currentPage - 1
                )
        }

        OnPaginationRightClick -> {
            viewState.value =
                (viewState.value as FinishedEventsViewState).copy(
                    currentPage =
                    (viewState.value as FinishedEventsViewState).currentPage + 1
                )

        }
        OnToggleApplicationToEventClick -> {}
    }

}
