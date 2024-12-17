package org.volonter.helpinghand.domain.repository

interface AuthenticationRepository {
    suspend fun login(email: String, password: String, navigate: () -> Unit)
    suspend fun register(
        name: String,
        email: String,
        password: String,
        isOrganisation: Boolean,
        navigate: () -> Unit
    )
}
