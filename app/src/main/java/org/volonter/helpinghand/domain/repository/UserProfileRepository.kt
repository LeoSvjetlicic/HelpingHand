package org.volonter.helpinghand.domain.repository

interface UserProfileRepository {
    suspend fun editUserProfile(
        imageLink: String,
        name: String,
        description: String
    ): Boolean
}