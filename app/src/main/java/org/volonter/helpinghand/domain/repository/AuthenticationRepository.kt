package org.volonter.helpinghand.domain.repository

interface AuthenticationRepository {
    fun login(email: String, password: String): Boolean
    suspend fun register(
        name: String,
        email: String,
        password: String,
        isOrganisation: Boolean
    ): Boolean
}
