package org.volonter.helpinghand.data.repository

import org.volonter.helpinghand.domain.repository.AuthenticationRepository
import org.volonter.helpinghand.domain.usecases.LoginUseCase
import org.volonter.helpinghand.domain.usecases.RegisterUseCase

class AuthenticationRepositoryImpl(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
) : AuthenticationRepository {
    override fun login(username: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun register(username: String, password: String, isOrganisation: Boolean): Boolean {
        TODO("Not yet implemented")
    }
}
