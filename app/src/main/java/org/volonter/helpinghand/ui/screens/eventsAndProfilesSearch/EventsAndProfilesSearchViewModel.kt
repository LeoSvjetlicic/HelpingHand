package org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.volonter.helpinghand.domain.repository.EventRepository
import org.volonter.helpinghand.domain.repository.UserProfileRepository
import javax.inject.Inject

@HiltViewModel
class EventsAndProfilesSearchViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val userProfileRepository: UserProfileRepository
) : ViewModel() {
    private val _eventsViewState = MutableStateFlow(EventsAndProfilesSearchViewState.EventsViewState(
        emptyList()
    ))
    private val _profilesViewState = MutableStateFlow(EventsAndProfilesSearchViewState.ProfilesViewState(
        emptyList()
    ))

    init {
        viewModelScope.launch {
            _eventsViewState.value = EventsAndProfilesSearchViewState.EventsViewState(
                events = eventRepository.getAllEventsForSearch()
            )
        }
        viewModelScope.launch {
            _profilesViewState.value = EventsAndProfilesSearchViewState.ProfilesViewState(
                profiles = userProfileRepository.getAllUsers()
            )
        }
    }

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

    var selectedTab = MutableStateFlow(0)

    fun setTab(tabIndex: Int) {
        selectedTab.value = tabIndex
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
}
