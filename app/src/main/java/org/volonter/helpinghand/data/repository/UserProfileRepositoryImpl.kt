package org.volonter.helpinghand.data.repository

import org.volonter.helpinghand.domain.repository.UserProfileRepository
import org.volonter.helpinghand.domain.usecases.EditUserProfileUseCase
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val editUserProfileUseCase: EditUserProfileUseCase
) : UserProfileRepository {
    override suspend fun editUserProfile(
        imageLink: String,
        name: String,
        description: String
    ): Boolean = editUserProfileUseCase.invoke(imageLink, name, description)
}