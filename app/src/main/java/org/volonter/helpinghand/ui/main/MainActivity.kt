package org.volonter.helpinghand.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.volonter.helpinghand.ui.screens.addevent.AddEventScreen
import org.volonter.helpinghand.ui.screens.addevent.AddEventViewModel
import org.volonter.helpinghand.ui.screens.addreview.AddReviewScreen
import org.volonter.helpinghand.ui.screens.eventdetails.EventDetailsScreen
import org.volonter.helpinghand.ui.screens.login.LoginScreen
import org.volonter.helpinghand.ui.screens.organizationProfile.OrganizationProfileScreen
import org.volonter.helpinghand.ui.screens.volunteerProfile.VolunteerProfileScreen
import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.EventsAndProfilesSearchScreen
import org.volonter.helpinghand.ui.screens.settings.SettingsScreen
import org.volonter.helpinghand.ui.theme.HelpingHandTheme
import org.volonter.helpinghand.ui.theme.PrimaryGreen
import org.volonter.helpinghand.utlis.Constants
import org.volonter.helpinghand.utlis.Constants.NavigationRoutes.ADD_EVENT_ROUTE
import org.volonter.helpinghand.utlis.Constants.NavigationRoutes.EVENT_DETAILS_ROUTE

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelpingHandTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PrimaryGreen)
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = EVENT_DETAILS_ROUTE
//                        if (FirebaseAuth.getInstance().currentUser != null) {
//                            Constants.NavigationRoutes.EVENT_DETAILS_ROUTE
//                        } else {
//                            Constants.NavigationRoutes.LOGIN_ROUTE
//                        }
                    ) {

                        composable(route = Constants.NavigationRoutes.LOGIN_ROUTE) {
                            LoginScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier,
                                navigate = {
                                    navController.navigate(Constants.NavigationRoutes.EVENT_DETAILS_ROUTE)
                                }
                            )
                        }
                        composable(route = ADD_EVENT_ROUTE) {
                            val viewModel = hiltViewModel<AddEventViewModel>()
                            AddEventScreen(
                                viewState = viewModel.inputViewState.value,
                                calendarViewState = viewModel.calendarViewState.value,
                                onPostClick = { },
                                onCancelClick = {},
                                onScreenAction = viewModel::onScreenAction,
                            )
                        }
                        composable(route = Constants.NavigationRoutes.EVENT_DETAILS_ROUTE) {
                            EventDetailsScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier,
                                onAddReviewClick = { navController.navigate(Constants.NavigationRoutes.ADD_REVIEW_ROUTE) },
                                onTitleClick = { navController.navigate(Constants.NavigationRoutes.ORGANIZATION_PROFILE_ROUTE) },
                                onUserClick = { navController.navigate(Constants.NavigationRoutes.VOLUNTEER_PROFILE_ROUTE) },
                                onSearchClick = { navController.navigate(Constants.NavigationRoutes.EVENTS_AND_PROFILES_SEARCH_ROUTE) },
                                onSettingsClick = { navController.navigate(Constants.NavigationRoutes.SETTINGS_ROUTE) }
                            )
                        }

                        composable(route = Constants.NavigationRoutes.ADD_REVIEW_ROUTE) {
                            AddReviewScreen(
                                viewModel = hiltViewModel(),
                                onCancelClick = { navController.popBackStack() },
                                onPostClick = { navController.popBackStack() }
                            )
                        }

                        composable(route = Constants.NavigationRoutes.ORGANIZATION_PROFILE_ROUTE) {
                            OrganizationProfileScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier
                            )
                        }

                        composable(route = Constants.NavigationRoutes.VOLUNTEER_PROFILE_ROUTE) {
                            VolunteerProfileScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier
                            )
                        }

                        composable(route = Constants.NavigationRoutes.EVENTS_AND_PROFILES_SEARCH_ROUTE) {
                            EventsAndProfilesSearchScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier
                            )
                        }

                        composable(route = Constants.NavigationRoutes.SETTINGS_ROUTE) {
                            SettingsScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        }
    }
}
