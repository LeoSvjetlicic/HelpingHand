package org.volonter.helpinghand.ui.screens.settings

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.volonter.helpinghand.R
import org.volonter.helpinghand.domain.repository.UserProfileRepository
import org.volonter.helpinghand.utlis.StringResourcesProvider
import org.volonter.helpinghand.utlis.ToastHelper
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val userProfileRepository: UserProfileRepository,
    private val toastHelper: ToastHelper,
    private val stringResourcesProvider: StringResourcesProvider,
): ViewModel() {
    var viewState = mutableStateOf(SettingsViewState())

    init {
        viewModelScope.launch {
            val result = userProfileRepository.getUserById(auth.currentUser?.uid ?: "")
            viewState.value = viewState.value.copy(
                newUsername = viewState.value.newUsername.copy(value = result.name),
                newImageLink = viewState.value.newImageLink.copy(value = result.imageLink),
                description = viewState.value.description.copy(value = result.description)
            )
        }
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

        is ChangeImageLink -> {
            viewState.value =
                viewState.value.copy(newImageLink = viewState.value.newImageLink.copy(value = action.value))
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
