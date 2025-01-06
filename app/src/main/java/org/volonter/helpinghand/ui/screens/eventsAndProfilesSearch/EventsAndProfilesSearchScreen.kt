package org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.components.EventsSearchList
import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.components.ProfilesSearchList
import org.volonter.helpinghand.ui.theme.LightBrown
import org.volonter.helpinghand.ui.theme.MiddleBrown


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EventsAndProfilesSearchScreen(
    viewModel: EventsAndProfilesSearchViewModel,
    modifier: Modifier = Modifier,
    onEventClick:(String) -> Unit,
    onUserClick: (String) -> Unit
) {
    val selectedTabIndex by viewModel.selectedTab.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    val filteredEvents by viewModel.filteredEvents.collectAsState()
    val filteredProfiles by viewModel.filteredProfiles.collectAsState()

    //viewModel.setTab(selectedTabIndex)

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(R.drawable.search_screen_image),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        val onActiveChange: (Boolean) -> Unit = { viewModel.onToggleSearch() }
        val colors1 = SearchBarDefaults.colors(
            containerColor = LightBrown,
            dividerColor = Color.Transparent,
        )
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color.Transparent),
            verticalArrangement = Arrangement.Top,
        ) {
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = searchText,
                    onQueryChange = viewModel::onSearchTextChange,
                    onSearch = viewModel::onSearchTextChange,
                    expanded = isSearching,
                    onExpandedChange = onActiveChange,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search Icon",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
            },
            expanded = false,
            onExpandedChange = onActiveChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            colors = colors1,
            content = {},
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(top = 24.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Column(modifier = Modifier.fillMaxSize()) {

                    val tabs = listOf("Events", "Profiles")
                    TabRow(
                        selectedTabIndex = selectedTabIndex,
                        modifier = Modifier.fillMaxWidth()
                            .padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
                        containerColor = MiddleBrown,
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
                        },
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { viewModel.setTab(index) },
                                text = { Text(text = title, color = Color.White) }
                            )
                        }
                    }

                    when (selectedTabIndex){
                        0 -> {
                            EventsSearchList(
                                viewState = EventsAndProfilesSearchViewState.EventsViewState(filteredEvents),
                                onEventClick
                            )
                        }
                        1 -> {
                            ProfilesSearchList(
                                viewState = EventsAndProfilesSearchViewState.ProfilesViewState(filteredProfiles),
                                onUserClick
                            )
                        }
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
