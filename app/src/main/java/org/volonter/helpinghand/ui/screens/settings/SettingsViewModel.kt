package org.volonter.helpinghand.ui.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.volonter.helpinghand.ui.screens.addreview.AddReviewCardActions
import org.volonter.helpinghand.ui.screens.addreview.ChangeBody
import org.volonter.helpinghand.ui.screens.addreview.ChangeRating
import org.volonter.helpinghand.ui.screens.addreview.ChangeTitle
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(): ViewModel() {
    var viewState = mutableStateOf(SettingsViewState())

    init {
//        TODO - load user data
    }

    fun onSettingsScreenAction(action: SettingsScreenActions) = when (action) {
        is ChangeDescription -> {
            viewState.value =
                viewState.value.copy(description = viewState.value.description.copy(value = action.value))
        }


        is ChangeUsername -> {
            viewState.value =
                viewState.value.copy(newUsername = viewState.value.newUsername.copy(value = action.value))
        }
    }

    fun onSaveClick() {

    }
}