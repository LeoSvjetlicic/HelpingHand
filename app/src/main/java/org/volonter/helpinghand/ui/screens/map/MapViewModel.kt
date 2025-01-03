package org.volonter.helpinghand.ui.screens.map

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.volonter.helpinghand.domain.repository.AuthenticationRepository
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {
    val searchInput = mutableStateOf("")
    fun onSearchInoutChange(newValue: String) {
        searchInput.value = newValue
    }

    val markers = mutableStateOf(
        listOf(
            MarkerViewState(
                markerState = MarkerState(
                    LatLng(45.560684, 18.695853)
                ),
                name = "Tvrda",
                description = "Tvrdavica",
                id = "lacabvkjsd"
            )
        )
    )
    var currentPosition = mutableStateOf(LatLng(45.5544, 18.6624))
    var supportedTowns = mutableStateOf(supportedPlaces)

    fun logout(navigate : () -> Unit) {
        repository.logout(navigate)
    }
}
