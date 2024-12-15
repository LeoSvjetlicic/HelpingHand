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

    fun onRatingChanged(rating: Int) {
        viewState.value = viewState.value.copy(rating = rating)
    }

    fun onPostClick() {

    }
}
