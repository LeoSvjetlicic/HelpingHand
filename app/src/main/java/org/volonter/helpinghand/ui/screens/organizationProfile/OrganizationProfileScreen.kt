package org.volonter.helpinghand.ui.screens.organizationProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.screens.eventdetails.components.IconTextElement
import org.volonter.helpinghand.ui.screens.organizationProfile.components.ProfilePagination
import org.volonter.helpinghand.ui.screens.organizationProfile.components.OrganizationEventElement

import org.volonter.helpinghand.ui.theme.MiddleBrown
import org.volonter.helpinghand.ui.theme.PrimaryGreen

@Composable
fun OrganizationProfileScreen(
    viewModel: OrganizationProfileViewModel,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MiddleBrown),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Column(modifier = Modifier.fillMaxSize()) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 26.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, top = 62.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .widthIn(max = 80.dp),
                                model = viewModel.viewState.value.imageLink,
                                contentScale = ContentScale.Fit,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                modifier = Modifier.padding(vertical = 16.dp),
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                text = viewModel.viewState.value.title,
                                color = Color.White
                            )
                        }


                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = viewModel.viewState.value.description,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                    if (viewModel.viewState.value is FinishedEventsViewState) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .padding(bottom = 8.dp, top = 20.dp, start = 32.dp)
                                    .align(Alignment.Start),
                                text = stringResource(R.string.previous_events),
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                getPaginationSublist(viewModel.viewState.value as FinishedEventsViewState)
                                    .forEach {
                                        OrganizationEventElement(
                                            eventViewState = it,
                                            modifier = Modifier.padding(horizontal = 32.dp),
                                            onClick = viewModel::onScreenAction
                                        )
                                    }
                            }
                            if ((viewModel.viewState.value as FinishedEventsViewState).allReviews.isNotEmpty()) {
                                ProfilePagination(
                                    viewState = viewModel.viewState.value as FinishedEventsViewState,
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    onScreenAction = viewModel::onScreenAction
                                )
                            }
                        }
                    }
                }
            }
        }
        val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
        Icon(
            modifier = Modifier.padding(start = 22.dp, top = 22.dp, end = 22.dp)
                .clickable(interactionSource, null) {
                viewModel.onScreenAction(OnBackClick)
            },
            painter = painterResource(R.drawable.ic_arrow_left),
            contentDescription = null
        )
    }
}

private fun getPaginationSublist(viewState: FinishedEventsViewState): List<EventViewState> {
    return if (viewState.eventsPerPage * viewState.currentPage > viewState.allReviews.size) {
        viewState.allReviews.subList(
            viewState.eventsPerPage * viewState.currentPage - viewState.eventsPerPage,
            viewState.allReviews.size
        )
    } else {
        viewState.allReviews.subList(
            viewState.eventsPerPage * viewState.currentPage - viewState.eventsPerPage,
            viewState.eventsPerPage * viewState.currentPage
        )
    }
}

