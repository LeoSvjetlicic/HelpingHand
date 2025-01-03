package org.volonter.helpinghand.ui.screens.settings

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.volonter.helpinghand.R
import org.volonter.helpinghand.domain.repository.UserProfileRepository
import org.volonter.helpinghand.ui.HelpingHand.Companion.stringResourcesProvider
import org.volonter.helpinghand.ui.screens.addreview.AddReviewCardActions
import org.volonter.helpinghand.ui.screens.addreview.ChangeBody
import org.volonter.helpinghand.ui.screens.addreview.ChangeRating
import org.volonter.helpinghand.ui.screens.addreview.ChangeTitle
import org.volonter.helpinghand.utlis.StringResourcesProvider
import org.volonter.helpinghand.utlis.ToastHelper
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository,
    private val toastHelper: ToastHelper,
    private val stringResourcesProvider: StringResourcesProvider
): ViewModel() {
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

    suspend fun onSaveClick(): Boolean {
        val result = userProfileRepository.editUserProfile(
            viewState.value.newImageLink.value,
            viewState.value.newUsername.value,
            viewState.value.description.value
        )
        if(result){
            toastHelper.createToast(
                stringResourcesProvider.getString(R.string.profile_updated),
                Toast.LENGTH_SHORT
            )
        } else {
            toastHelper.createToast(
                stringResourcesProvider.getString(R.string.profile_not_updated),
                Toast.LENGTH_SHORT
            )
        }
        return result
    }
}