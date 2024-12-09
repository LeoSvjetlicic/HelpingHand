package org.volonter.helpinghand.domain.repository

interface AuthenticationRepository {
    fun login(username: String, password: String): Boolean
    fun register(username: String, password: String, isOrganisation: Boolean): Boolean
}
