package org.volonter.helpinghand.ui.screens.volunteerProfile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.volonter.helpinghand.domain.repository.EventRepository
import javax.inject.Inject
@HiltViewModel
class VolunteerProfileViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {
    private val finishedEventsViewState = FinishedEventsViewState()
    private val inProgressEventsViewState = InProgressEventsViewState()

    var viewState: MutableState<VolunteerProfileViewState> =
        mutableStateOf(inProgressEventsViewState)

    var selectedTabIndex: MutableState<Int> = mutableStateOf(0)

    var isLoading: MutableState<Boolean> = mutableStateOf(true)
        private set

    fun updateViewState(volunteerId: String) {
        viewModelScope.launch {
            isLoading.value = true
            val result = eventRepository.getEventsByVolunteerId(volunteerId)
            if (result != null) {

                val (finishedEvents, inProgressEvents) = result
                finishedEventsViewState.events = finishedEvents.events
                finishedEventsViewState.id = finishedEvents.id
                finishedEventsViewState.title = finishedEvents.title
                finishedEventsViewState.imageLink = finishedEvents.imageLink

                inProgressEventsViewState.events = inProgressEvents.events
                inProgressEventsViewState.id = inProgressEvents.id
                inProgressEventsViewState.title = inProgressEvents.title
                inProgressEventsViewState.imageLink = inProgressEvents.imageLink

                println("Finished events: ${finishedEvents}")
            }
            isLoading.value = false
        }
    }


    fun setTab(tabIndex: Int) {
        selectedTabIndex.value = tabIndex
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