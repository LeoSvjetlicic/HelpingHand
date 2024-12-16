package org.volonter.helpinghand.ui.screens.eventdetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor() : ViewModel() {
    var viewState: MutableState<EventDetailsViewState> =
        mutableStateOf(FinishedEventDetailsViewState())

    fun onScreenAction(action: EventScreenActions) = when (action) {
        OnAddReviewClick -> {}
        OnBackClick -> {}
        OnCallClick -> {}
        OnLocationClick -> {}
        is OnOrganisationClick -> {}
        OnPaginationLeftClick -> {
            viewState.value =
                (viewState.value as FinishedEventDetailsViewState).copy(
                    currentPage =
                    (viewState.value as FinishedEventDetailsViewState).currentPage - 1
                )
        }

        OnPaginationRightClick -> {
            viewState.value =
                (viewState.value as FinishedEventDetailsViewState).copy(
                    currentPage =
                    (viewState.value as FinishedEventDetailsViewState).currentPage + 1
                )

        }

        is OnReviewUserClick -> {}
        OnToggleApplicationToEventClick -> {}
    }

}
