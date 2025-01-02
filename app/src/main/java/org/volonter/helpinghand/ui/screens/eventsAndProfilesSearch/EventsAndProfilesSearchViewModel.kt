package org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EventsAndProfilesSearchViewModel @Inject constructor() : ViewModel() {
    private val _eventsViewState = MutableStateFlow(EventsAndProfilesSearchViewState.EventsViewState())
    private val _profilesViewState = MutableStateFlow(EventsAndProfilesSearchViewState.ProfilesViewState())

    private val _viewState: MutableStateFlow<EventsAndProfilesSearchViewState> =
        MutableStateFlow(_eventsViewState.value)
    val viewState = _viewState.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val filteredEvents = _searchText
        .combine(_eventsViewState) { text, state ->
            if (text.isBlank()) {
                state.events
            } else {
                state.events.filter { event ->
                    event.title.contains(text.trim(), ignoreCase = true)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _eventsViewState.value.events
        )

    val filteredProfiles = _searchText
        .combine(_profilesViewState) { text, state ->
            if (text.isBlank()) {
                state.profiles
            } else {
                state.profiles.filter { profile ->
                    profile.name.contains(text.trim(), ignoreCase = true)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _profilesViewState.value.profiles
        )

    fun setTab(tabIndex: Int) {
        _viewState.value = when (tabIndex) {
            0 -> _eventsViewState.value
            1 -> _profilesViewState.value
            else -> _viewState.value
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onToggleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }

    fun onScreenAction(action: EventsAndProfilesSearchActions) {
        when (action) {
            is OnEventUserClick -> {  }
            is OnProfileUserClick -> {  }
            OnBackClick -> {}
        }
    }
}
