package org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.EventsAndProfilesSearchViewModel
import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.EventsAndProfilesSearchViewState


@Composable
fun ProfilesSearchList(viewState: EventsAndProfilesSearchViewState.ProfilesViewState, viewModel: EventsAndProfilesSearchViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            viewState.profiles.forEach {
                ProfileElement(
                    userViewState = it,
                    modifier = Modifier.padding(horizontal = 32.dp),
                    onClick = viewModel::onScreenAction
                )
            }
        }
    }
}