package org.volonter.helpinghand.domain.repository

import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.UserViewState

interface UserProfileRepository {
    suspend fun editUserProfile(
        imageLink: String,
        name: String,
        description: String
    ): Boolean


    suspend fun getAllUsers(): List<UserViewState>
}