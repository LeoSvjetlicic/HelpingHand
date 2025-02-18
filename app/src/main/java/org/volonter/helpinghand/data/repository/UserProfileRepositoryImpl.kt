package org.volonter.helpinghand.data.repository

import org.volonter.helpinghand.domain.repository.UserProfileRepository
import org.volonter.helpinghand.domain.usecases.EditUserProfileUseCase
import org.volonter.helpinghand.domain.usecases.GetAllUsersUseCase
import org.volonter.helpinghand.domain.usecases.GetUserDetailsUseCase
import org.volonter.helpinghand.ui.common.viewstates.UserViewState
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val editUserProfileUseCase: EditUserProfileUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getUserDetailsUseCase: GetUserDetailsUseCase
) : UserProfileRepository {
    override suspend fun editUserProfile(
        imageLink: String,
        name: String,
        description: String
    ): Boolean = editUserProfileUseCase.invoke(imageLink, name, description)

    override suspend fun getAllUsers(): List<UserViewState> = getAllUsersUseCase.invoke()
    override suspend fun getUserById(id: String): UserViewState =
        getUserDetailsUseCase.invoke(id)
}
