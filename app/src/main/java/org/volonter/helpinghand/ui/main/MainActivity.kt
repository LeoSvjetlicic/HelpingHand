package org.volonter.helpinghand.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.volonter.helpinghand.ui.screens.addevent.AddEventScreen
import org.volonter.helpinghand.ui.screens.addevent.AddEventViewModel
import org.volonter.helpinghand.ui.screens.addreview.AddReviewScreen
import org.volonter.helpinghand.ui.screens.addreview.AddReviewViewModel
import org.volonter.helpinghand.ui.screens.eventdetails.EventDetailsScreen
import org.volonter.helpinghand.ui.screens.eventdetails.EventDetailsViewModel
import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.EventsAndProfilesSearchScreen
import org.volonter.helpinghand.ui.screens.login.LoginScreen
import org.volonter.helpinghand.ui.screens.map.LogoutClick
import org.volonter.helpinghand.ui.screens.map.MapScreen
import org.volonter.helpinghand.ui.screens.map.MapViewModel
import org.volonter.helpinghand.ui.screens.map.MyProfileClick
import org.volonter.helpinghand.ui.screens.map.SettingsClick
import org.volonter.helpinghand.ui.screens.organizationProfile.OrganizationProfileScreen
import org.volonter.helpinghand.ui.screens.settings.SettingsScreen
import org.volonter.helpinghand.ui.screens.settings.SettingsViewModel
import org.volonter.helpinghand.ui.screens.volunteerProfile.VolunteerProfileScreen
import org.volonter.helpinghand.ui.theme.HelpingHandTheme
import org.volonter.helpinghand.ui.theme.PrimaryGreen
import org.volonter.helpinghand.utlis.Constants
import org.volonter.helpinghand.utlis.Constants.NavigationRoutes.ADD_EVENT_ROUTE
import org.volonter.helpinghand.utlis.Constants.NavigationRoutes.ADD_REVIEW_ROUTE
import org.volonter.helpinghand.utlis.Constants.NavigationRoutes.EVENTS_AND_PROFILES_SEARCH_ROUTE
import org.volonter.helpinghand.utlis.Constants.NavigationRoutes.EVENT_DETAILS_ROUTE
import org.volonter.helpinghand.utlis.Constants.NavigationRoutes.EVENT_DETAILS_ROUTE_FULL
import org.volonter.helpinghand.utlis.Constants.NavigationRoutes.EVENT_ID
import org.volonter.helpinghand.utlis.Constants.NavigationRoutes.MAP_ROUTE
import org.volonter.helpinghand.utlis.Constants.NavigationRoutes.ORGANIZATION_PROFILE_ROUTE
import org.volonter.helpinghand.utlis.Constants.NavigationRoutes.SETTINGS_ROUTE
import org.volonter.helpinghand.utlis.Constants.NavigationRoutes.VOLUNTEER_PROFILE_ROUTE
import org.volonter.helpinghand.utlis.Constants.SharedPreferencesConstants.SHARED_PREFERENCES_IS_ORGANISATION
import org.volonter.helpinghand.utlis.SharedPreferencesHelper

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelpingHandTheme {
                val navController = rememberNavController()
                val navBackStack by navController.currentBackStackEntryAsState()
                val currentDestination by remember { derivedStateOf { navBackStack?.destination?.route } }
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PrimaryGreen),
                    floatingActionButton = {
                        if (currentDestination == MAP_ROUTE &&
                            SharedPreferencesHelper.getBooleanFromSharedPrefs(
                                SHARED_PREFERENCES_IS_ORGANISATION
                            )
                        ) {
                            FloatingActionButton(
                                modifier = Modifier.padding(bottom = 60.dp),
                                onClick = {
                                    navController.navigate(ADD_EVENT_ROUTE)
                                },
                                containerColor = PrimaryGreen
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Start
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination =
                        if (FirebaseAuth.getInstance().currentUser != null) {
                            MAP_ROUTE
                        } else {
                           Constants.NavigationRoutes.LOGIN_ROUTE
                       }
                    ) {

                        composable(route = Constants.NavigationRoutes.LOGIN_ROUTE) {
                            LoginScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier,
                                navigate = {
                                    navController.navigate(MAP_ROUTE)
                                }
                            )
                        }

                        composable(MAP_ROUTE) {
                            val viewModel = hiltViewModel<MapViewModel>()
                            MapScreen(
                                currentPosition = viewModel.currentPosition.value,
                                supportedPlaces = viewModel.supportedTowns.value,
                                searchInput = viewModel.searchInput.value,
                                onSearchInputChange = viewModel::onSearchInoutChange,
                                markers = viewModel.markers.value,
                                onEventClick = { eventId ->
                                    navController.navigate("$EVENT_DETAILS_ROUTE/$eventId")
                                },
                                onPopupMenuClick = { popupSelectable ->
                                    when (popupSelectable) {
                                        MyProfileClick -> {
                                            if (SharedPreferencesHelper.getBooleanFromSharedPrefs(
                                                    SHARED_PREFERENCES_IS_ORGANISATION
                                                )
                                            ) {
                                                navController.navigate(ORGANIZATION_PROFILE_ROUTE)
                                            } else {
                                                navController.navigate(VOLUNTEER_PROFILE_ROUTE)
                                            }
                                        }

                                        SettingsClick -> navController.navigate(SETTINGS_ROUTE)

                                        LogoutClick -> viewModel.logout {
                                            navController.navigate(Constants.NavigationRoutes.LOGIN_ROUTE)
                                        }
                                    }
                                },
                                modifier = Modifier.fillMaxSize()
                            ) {
                                navController.navigate(EVENTS_AND_PROFILES_SEARCH_ROUTE)
                            }
                        }
                        composable(route = ADD_EVENT_ROUTE) {
                            val viewModel = hiltViewModel<AddEventViewModel>()
                            AddEventScreen(
                                context = this@MainActivity,
                                viewState = viewModel.inputViewState.value,
                                calendarViewState = viewModel.calendarViewState.value,
                                onPostClick = {
                                    lifecycleScope.launch {
                                        if (viewModel.onPostClick()) {
                                            navController.popBackStack()
                                        }
                                    }
                                },
                                onCancelClick = { navController.popBackStack() },
                                onScreenAction = viewModel::onScreenAction,
                            )
                        }
                        composable(
                            route = EVENT_DETAILS_ROUTE_FULL,
                            arguments = listOf(navArgument(EVENT_ID) { type = NavType.StringType })
                        ) { backStackEntry ->
                            val eventId = backStackEntry.arguments?.getString("eventId") ?: ""
                            val viewModel = hiltViewModel<EventDetailsViewModel>()
                            viewModel.updateViewState(eventId)
                            EventDetailsScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier,
                                onAddReviewClick = { navController.navigate(ADD_REVIEW_ROUTE) },
                                onTitleClick = { navController.navigate(ORGANIZATION_PROFILE_ROUTE) },
                                onUserClick = { navController.navigate(VOLUNTEER_PROFILE_ROUTE) },
                                onSearchClick = { navController.navigate(EVENTS_AND_PROFILES_SEARCH_ROUTE) },
                                onSettingsClick = { navController.navigate(SETTINGS_ROUTE) }
                            )
                        }

                        composable(route = ADD_REVIEW_ROUTE) {
                            val viewModel = hiltViewModel<AddReviewViewModel>()
                            AddReviewScreen(
                                viewModel = hiltViewModel(),
                                onCancelClick = { navController.popBackStack() },
                                onPostClick = {
                                    lifecycleScope.launch {
                                        if (viewModel.onPostClick()) {
                                            navController.popBackStack()
                                        }
                                    }
                                }
                            )
                        }

                        composable(route = ORGANIZATION_PROFILE_ROUTE) {
                            OrganizationProfileScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier
                            )
                        }

                        composable(route = VOLUNTEER_PROFILE_ROUTE) {
                            VolunteerProfileScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier
                            )
                        }

                        composable(route = EVENTS_AND_PROFILES_SEARCH_ROUTE) {
                            EventsAndProfilesSearchScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier
                            )
                        }

                        composable(route = SETTINGS_ROUTE) {
                            val viewModel = hiltViewModel<SettingsViewModel>()
                            SettingsScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier,
                                onSaveClick = {
                                    lifecycleScope.launch {
                                        if (viewModel.onSaveClick()) {
                                            navController.popBackStack()
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
