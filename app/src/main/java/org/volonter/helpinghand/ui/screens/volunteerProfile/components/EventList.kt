package org.volonter.helpinghand.ui.screens.volunteerProfile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.ui.screens.volunteerProfile.EventViewState
import org.volonter.helpinghand.ui.screens.volunteerProfile.PaginatedViewState
import org.volonter.helpinghand.ui.screens.volunteerProfile.VolunteerProfileViewModel

@Composable
fun EventList(
    viewState: PaginatedViewState,
    viewModel: VolunteerProfileViewModel,
    onElementClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            getPaginationSublist(viewState).forEach {
                VolunteerEventElement(
                    eventViewState = it,
                    viewState = viewState,
                    modifier = Modifier.padding(horizontal = 32.dp),
                    onClick = onElementClick
                )
            }
        }
        if (viewState.events.isNotEmpty()) {
            VolunteerProfilePagination(
                viewState = viewState,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onScreenAction = viewModel::onScreenAction
            )
        }
    }
}


private fun getPaginationSublist(viewState: PaginatedViewState): List<EventViewState> {
    return if (viewState.eventsPerPage * viewState.currentPage > viewState.events.size) {
        viewState.events.subList(
            viewState.eventsPerPage * viewState.currentPage - viewState.eventsPerPage,
            viewState.events.size
        )
    } else {
        viewState.events.subList(
            viewState.eventsPerPage * viewState.currentPage - viewState.eventsPerPage,
            viewState.eventsPerPage * viewState.currentPage
        )
    }
}
