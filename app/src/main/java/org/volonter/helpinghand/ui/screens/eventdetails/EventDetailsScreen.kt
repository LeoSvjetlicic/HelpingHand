package org.volonter.helpinghand.ui.screens.eventdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.components.UserShortDetails
import org.volonter.helpinghand.ui.common.viewstates.ReviewViewState
import org.volonter.helpinghand.ui.screens.eventdetails.components.AddReviewButton
import org.volonter.helpinghand.ui.screens.eventdetails.components.EventDetailsTopBar
import org.volonter.helpinghand.ui.screens.eventdetails.components.IconTextElement
import org.volonter.helpinghand.ui.screens.eventdetails.components.Pagination
import org.volonter.helpinghand.ui.screens.eventdetails.components.ReviewItem
import org.volonter.helpinghand.ui.theme.PrimaryGreen

@Composable
fun EventDetailsScreen(
    viewModel: EventDetailsViewModel,
    modifier: Modifier = Modifier,
    onAddReviewClick: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryGreen),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Column(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 300.dp),
                        model = viewModel.viewState.value.imageLink,
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 26.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 16.dp),
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            text = viewModel.viewState.value.title,
                            color = Color.White
                        )
                        Text(
                            fontSize = 16.sp,
                            text = stringResource(R.string.organiser),
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        UserShortDetails(
                            viewState = viewModel.viewState.value.organisation,
                            onClick = {
                                viewModel.onScreenAction(
                                    OnOrganisationClick(it)
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        IconTextElement(
                            vectorId = R.drawable.ic_map_pin,
                            label = viewModel.viewState.value.location
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        IconTextElement(
                            vectorId = R.drawable.ic_phone,
                            label = viewModel.viewState.value.callingNumber
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        IconTextElement(
                            vectorId = R.drawable.ic_clock,
                            label = viewModel.viewState.value.date
                        )
                        if (viewModel.viewState.value is UnfinishedEventDetailsViewState) {
                            Spacer(modifier = Modifier.height(12.dp))
                            IconTextElement(
                                vectorId = R.drawable.ic_person,
                                label = stringResource(R.string.applicants) + getApplicants(
                                    viewState = viewModel.viewState.value as UnfinishedEventDetailsViewState
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = viewModel.viewState.value.description,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                    if (viewModel.viewState.value is FinishedEventDetailsViewState) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .padding(bottom = 8.dp, top = 20.dp)
                                    .align(Alignment.CenterHorizontally),
                                text = stringResource(R.string.user_reviews),
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                getPaginationSublist(viewModel.viewState.value as FinishedEventDetailsViewState)
                                    .forEach {
                                        ReviewItem(
                                            reviewViewState = it,
                                            modifier = Modifier.padding(horizontal = 32.dp),
                                            onClick = viewModel::onScreenAction
                                        )
                                    }
                            }
                            if ((viewModel.viewState.value as FinishedEventDetailsViewState).allReviews.isNotEmpty()) {
                                Pagination(
                                    viewState = viewModel.viewState.value as FinishedEventDetailsViewState,
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    onScreenAction = viewModel::onScreenAction
                                )
                            }
                        }
                    }
                }
            }
        }
        EventDetailsTopBar(
            viewState = viewModel.viewState.value,
            showApplyButton = viewModel.viewState.value is UnfinishedEventDetailsViewState,
            onBackClick = { viewModel.onScreenAction(OnBackClick) },
            onToggleApplicationToEventClick = {
                viewModel.onScreenAction(
                    OnToggleApplicationToEventClick
                )
            }
        )
        if (viewModel.viewState.value is FinishedEventDetailsViewState) {
            AddReviewButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onAddReviewClick = onAddReviewClick
            )
        }
    }
}

private fun getPaginationSublist(viewState: FinishedEventDetailsViewState): List<ReviewViewState> {
    return if (viewState.reviewsPerPage * viewState.currentPage > viewState.allReviews.size) {
        viewState.allReviews.subList(
            viewState.reviewsPerPage * viewState.currentPage - viewState.reviewsPerPage,
            viewState.allReviews.size
        )
    } else {
        viewState.allReviews.subList(
            viewState.reviewsPerPage * viewState.currentPage - viewState.reviewsPerPage,
            viewState.reviewsPerPage * viewState.currentPage
        )
    }
}

private fun getApplicants(viewState: UnfinishedEventDetailsViewState): String {
    return ": ${(viewState).appliedVolunteers}/" +
            "${(viewState).neededVolunteers}"
}
