package org.volonter.helpinghand.ui.screens.addreview

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddReviewViewModel @Inject constructor() : ViewModel() {
    var viewState = mutableStateOf(AddReviewViewState())

    init {
//        TODO - load user data
    }

    fun onAddReviewScreenAction(action: AddReviewCardActions) = when (action) {
        is ChangeBody -> {
            viewState.value = viewState.value.copy(body = action.value)
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
            viewState.value = viewState.value.copy(title = action.value)
        }
    }

    fun onPostClick() {

    }
}
