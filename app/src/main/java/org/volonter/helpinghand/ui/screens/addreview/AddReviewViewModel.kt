package org.volonter.helpinghand.ui.screens.addreview

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.volonter.helpinghand.R
import org.volonter.helpinghand.data.repository.UserProfileRepositoryImpl
import org.volonter.helpinghand.domain.repository.ReviewRepository
import org.volonter.helpinghand.ui.common.viewstates.InputFieldState
import org.volonter.helpinghand.utlis.StringResourcesProvider
import org.volonter.helpinghand.utlis.ToastHelper
import javax.inject.Inject

@HiltViewModel
class AddReviewViewModel @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    private val reviewRepository: ReviewRepository,
    private val userProfileRepositoryImpl: UserProfileRepositoryImpl,
    private val toastHelper: ToastHelper,
    private val stringResourcesProvider: StringResourcesProvider
) : ViewModel() {
    var viewState = mutableStateOf(AddReviewViewState())
    var eventId = mutableStateOf("")
    init {
        viewModelScope.launch {
            viewState.value = viewState.value.copy(
                user = userProfileRepositoryImpl.getUserById(
                    firebaseAuth.currentUser?.uid ?: ""
                )
            )
        }
    }

    suspend fun onPostClick(): Boolean {
        if (!validateInputs()) return false
        val result = reviewRepository.addReview(
            eventId.value,
            viewState.value
        )
        if (result) {
            toastHelper.createToast(
                stringResourcesProvider.getString(R.string.review_success),
                Toast.LENGTH_SHORT
            )
        } else {
            toastHelper.createToast(
                stringResourcesProvider.getString(R.string.review_failed),
                Toast.LENGTH_SHORT
            )
        }
        return result
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        with(viewState.value) {
            fun validateField(inputField: InputFieldState): InputFieldState {
                return if (inputField.value.isBlank()) {
                    isValid = false
                    inputField.copy(error = stringResourcesProvider.getString(org.volonter.helpinghand.R.string.this_field_cant_be_empty))
                } else {
                    inputField.copy(error = "")
                }
            }

            viewState.value = viewState.value.copy(
                title = validateField(title),
                body = validateField(body)
            )
            if (rating == 0) {
                isValid = false
                toastHelper.createToast(
                    stringResourcesProvider.getString(R.string.rating_cannot_be_zero),
                    Toast.LENGTH_SHORT
                )
            }
        }

        return isValid
    }

    fun onAddReviewScreenAction(action: AddReviewCardActions) = when (action) {
        is ChangeBody -> {
            viewState.value =
                viewState.value.copy(body = viewState.value.body.copy(value = action.value))
        }

        is ChangeRating -> {
            viewState.value = viewState.value.copy(
                rating = if (viewState.value.rating == action.value) {
                    0
                } else {
                    action.value
                }
            )
        }

        is ChangeTitle -> {
            viewState.value =
                viewState.value.copy(title = viewState.value.title.copy(value = action.value))
        }
    }

}
