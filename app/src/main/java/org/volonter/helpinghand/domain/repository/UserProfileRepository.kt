package org.volonter.helpinghand.domain.repository

import org.volonter.helpinghand.ui.common.viewstates.UserViewState

interface UserProfileRepository {
    suspend fun editUserProfile(
        imageLink: String,
        name: String,
        description: String
    ): Boolean

    suspend fun getAllUsers(): List<UserViewState>
    suspend fun getUserById(id: String): UserViewState
}
