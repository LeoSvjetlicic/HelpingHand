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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.volonter.helpinghand.ui.screens.login.AuthenticationViewModel
import org.volonter.helpinghand.ui.screens.login.LoginScreen
import org.volonter.helpinghand.ui.screens.addreview.AddReviewScreen
import org.volonter.helpinghand.ui.screens.eventdetails.EventDetailsScreen
import org.volonter.helpinghand.ui.theme.HelpingHandTheme
import org.volonter.helpinghand.ui.theme.PrimaryGreen
import org.volonter.helpinghand.utlis.Constants

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
                        startDestination = Constants.NavigationRoutes.EVENT_DETAILS_ROUTE
                    ) {
                        composable(route = Constants.NavigationRoutes.EVENT_DETAILS_ROUTE) {
                            EventDetailsScreen(
                                viewModel = hiltViewModel(),
                                modifier = Modifier,
                                onAddReviewClick = { navController.navigate(Constants.NavigationRoutes.ADD_REVIEW_ROUTE) }
                            )
                        }

                        composable(route = Constants.NavigationRoutes.ADD_REVIEW_ROUTE) {
                            AddReviewScreen(
                                viewModel = hiltViewModel(),
                                onCancelClick = { navController.popBackStack() },
                                onPostClick = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
