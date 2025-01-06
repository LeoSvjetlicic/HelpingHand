package org.volonter.helpinghand.ui.screens.organizationProfile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.volonter.helpinghand.domain.repository.EventRepository
import javax.inject.Inject

@HiltViewModel
class OrganizationProfileViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {
    var viewState: MutableState<FinishedEventsViewState> =
        mutableStateOf(FinishedEventsViewState(allReviews = emptyList()))

    fun updateViewState(organisationId: String) {
        viewModelScope.launch { viewState.value = eventRepository.getFinishedEventsWithRatings(organisationId) ?: viewState.value }
    }

    fun onScreenAction(action: OrganizationScreenActions) = when (action) {
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
