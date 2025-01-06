package org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.EventsAndProfilesSearchViewState


@Composable
fun EventsSearchList(
    viewState: EventsAndProfilesSearchViewState.EventsViewState,
    onEventClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            viewState.events.forEach {
                EventElement(
                    eventViewState = it,
                    modifier = Modifier.padding(horizontal = 32.dp),
                    onClick = { id -> onEventClick(id) }
                )
            }
        }
    }
}
