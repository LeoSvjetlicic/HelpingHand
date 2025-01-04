package org.volonter.helpinghand.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.volonter.helpinghand.data.repository.UserProfileRepositoryImpl
import org.volonter.helpinghand.domain.repository.UserProfileRepository
import org.volonter.helpinghand.domain.usecases.EditUserProfileUseCase
import org.volonter.helpinghand.domain.usecases.GetAllUsersUseCase
import org.volonter.helpinghand.domain.usecases.GetUserDetailsUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserProfileModule {

    @Provides
    @Singleton
    fun provideEditUserProfileUseCase(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): EditUserProfileUseCase = EditUserProfileUseCase(firebaseAuth, firestore)

    @Provides
    @Singleton
    fun provideUserProfileRepository(
        editUserProfileUseCase: EditUserProfileUseCase,
        getAllUsersUseCase: GetAllUsersUseCase,
        getUserDetailsUseCase: GetUserDetailsUseCase
    ): UserProfileRepository =
        UserProfileRepositoryImpl(editUserProfileUseCase, getAllUsersUseCase, getUserDetailsUseCase)

}
