package org.volonter.helpinghand.ui.screens.volunteerProfile

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.viewstates.EventViewState
import org.volonter.helpinghand.ui.screens.eventdetails.components.IconTextElement
import org.volonter.helpinghand.ui.screens.volunteerProfile.components.EventList
import org.volonter.helpinghand.ui.screens.volunteerProfile.components.VolunteerEventElement
import org.volonter.helpinghand.ui.screens.volunteerProfile.components.VolunteerProfilePagination
import org.volonter.helpinghand.ui.theme.DarkBrown
import org.volonter.helpinghand.ui.theme.LightBrown
import org.volonter.helpinghand.ui.theme.MiddleBrown

@Composable
fun VolunteerProfileScreen(
    viewModel: VolunteerProfileViewModel,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    viewModel.setTab(selectedTabIndex)

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
                            .padding(horizontal = 26.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = viewModel.viewState.value.imageLink,
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = viewModel.viewState.value.volunteer.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.White
                        )
                    }

                    val tabs = listOf("In progress", "Finished")
                    TabRow(
                        selectedTabIndex = selectedTabIndex,
                        modifier = Modifier.fillMaxWidth()
                            .padding(bottom = 24.dp,
                                start = 16.dp,
                                end = 16.dp),
                        containerColor = DarkBrown,
                        contentColor = Color.White,
                        indicator = { tabPositions ->
                            Box(
                                Modifier
                                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                                    .height(4.dp)
                                    .background(LightBrown)
                            )
                        },
                        divider = {
                            HorizontalDivider(
                                thickness = 0.dp,
                            )
                        }
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                text = { Text(text = title, color = Color.White) }
                            )
                        }
                    }

                    when (viewModel.viewState.value) {
                        is InProgressEventsViewState -> {
                            EventList(viewState = viewModel.viewState.value as InProgressEventsViewState, viewModel)
                        }
                        is FinishedEventsViewState -> {
                            EventList(viewState = viewModel.viewState.value as FinishedEventsViewState, viewModel)
                        }
                    }
                }
            }
        }

        Icon(
            modifier = Modifier
                .padding(start = 22.dp, top = 22.dp, end = 22.dp)
                .clickable {
                    viewModel.onScreenAction(OnBackClick)
                },
            painter = painterResource(R.drawable.ic_arrow_left),
            contentDescription = null
        )
    }
}



