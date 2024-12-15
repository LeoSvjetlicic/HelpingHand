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
import dagger.hilt.android.AndroidEntryPoint
import org.volonter.helpinghand.ui.screens.eventdetails.EventDetailsScreen
import org.volonter.helpinghand.ui.theme.HelpingHandTheme
import org.volonter.helpinghand.ui.theme.PrimaryGreen

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
                    EventDetailsScreen(
                        hiltViewModel(),
                        modifier = Modifier.padding(innerPadding),
                        onAddReviewClick = {})
                }
            }
        }
    }
}
