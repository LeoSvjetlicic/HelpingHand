package org.volonter.helpinghand.ui.screens.volunteerProfile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class VolunteerProfileViewModel @Inject constructor() : ViewModel() {
    private val finishedEventsViewState = FinishedEventsViewState()
    private val inProgressEventsViewState = InProgressEventsViewState()

    var viewState: MutableState<VolunteerProfileViewState> =
        mutableStateOf(finishedEventsViewState)

    fun setTab(tabIndex: Int) {
        viewState.value = when (tabIndex) {
            0 -> inProgressEventsViewState
            1 -> finishedEventsViewState
            else -> viewState.value
        }
    }

    fun onScreenAction(action: VolunteerScreenActions) {
        when (action) {
            is OnEventUserClick -> { }
            OnBackClick -> {}
            OnPaginationLeftClick -> updatePagination(-1)
            OnPaginationRightClick -> updatePagination(1)
            OnToggleApplicationToEventClick -> { }
        }
    }

    private fun updatePagination(delta: Int) {
        if (viewState.value is PaginatedViewState) {
            val currentPaginatedState = viewState.value as PaginatedViewState
            val newPage = (currentPaginatedState.currentPage + delta).coerceAtLeast(1)
            viewState.value = when (viewState.value) {
                is FinishedEventsViewState -> (viewState.value as FinishedEventsViewState).copy(currentPage = newPage)
                is InProgressEventsViewState -> (viewState.value as InProgressEventsViewState).copy(currentPage = newPage)
                else -> viewState.value
            }
        }
    }
}