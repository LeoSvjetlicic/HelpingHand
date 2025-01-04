package org.volonter.helpinghand.ui.screens.eventdetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.volonter.helpinghand.domain.repository.EventRepository
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val eventRepositoryImpl: EventRepository
) : ViewModel() {
    var viewState: MutableState<EventDetailsViewState> =
        mutableStateOf(FinishedEventDetailsViewState())
    fun onScreenAction(action: EventScreenActions) {
        when (action) {
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

            is OnToggleApplicationToEventClick -> {
                viewModelScope.launch {
                    val result = eventRepositoryImpl.toggleApplication(viewState.value.id)
                    if (result.isSuccess) {
                        updateViewState(viewState.value.id)
                    } else {
                        result.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }

    fun updateViewState(eventId: String) {
        viewModelScope.launch { viewState.value = eventRepositoryImpl.getEventById(eventId) }
    }
}
