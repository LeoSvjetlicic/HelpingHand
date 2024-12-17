package org.volonter.helpinghand.data.repository

import org.volonter.helpinghand.domain.repository.AuthenticationRepository
import org.volonter.helpinghand.domain.usecases.LoginUseCase
import org.volonter.helpinghand.domain.usecases.RegisterUseCase

class AuthenticationRepositoryImpl(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
) : AuthenticationRepository {
    override suspend fun login(
        email: String,
        password: String,
        navigate: () -> Unit
    ) {
        return loginUseCase.invoke(email, password, navigate)
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        isOrganisation: Boolean,
        navigate: () -> Unit
    ) = registerUseCase.invoke(name, email, password, isOrganisation, navigate)
}
