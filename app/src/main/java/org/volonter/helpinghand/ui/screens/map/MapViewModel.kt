package org.volonter.helpinghand.ui.screens.map

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.volonter.helpinghand.domain.repository.AuthenticationRepository
import org.volonter.helpinghand.domain.repository.EventRepository
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: AuthenticationRepository,
    private val eventRepository: EventRepository,
) : ViewModel() {
    val searchInput = mutableStateOf("")
    val markers = mutableStateOf<List<MarkerViewState>>(listOf())
    var currentPosition = mutableStateOf(LatLng(45.5544, 18.6624))
    var supportedTowns = mutableStateOf(emptyMap<String, LatLng>())

    init {
        viewModelScope.launch {
            supportedTowns.value = eventRepository.getSupportedCities()
            println("fetchedGafasa: ${supportedTowns.value}")
        }
        viewModelScope.launch {
            markers.value = eventRepository.getAllEvents()
            println("fetchedGafaas: ${markers.value}")
        }
    }

    fun onSearchInoutChange(newValue: String) {
        searchInput.value = newValue
    }

    fun logout(navigate : () -> Unit) {
        repository.logout(navigate)
    }
}
