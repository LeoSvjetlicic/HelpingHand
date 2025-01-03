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
    fun provideUserProfileRepository(editUserProfileUseCase: EditUserProfileUseCase): UserProfileRepository =
        UserProfileRepositoryImpl(editUserProfileUseCase)

}