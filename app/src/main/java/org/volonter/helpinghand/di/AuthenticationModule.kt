package org.volonter.helpinghand.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.volonter.helpinghand.data.repository.AuthenticationRepositoryImpl
import org.volonter.helpinghand.domain.repository.AuthenticationRepository
import org.volonter.helpinghand.domain.usecases.LoginUseCase
import org.volonter.helpinghand.domain.usecases.RegisterUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(
        auth: FirebaseAuth
    ): LoginUseCase = LoginUseCase(auth)

    @Provides
    @Singleton
    fun provideRepositoryUseCase(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): RegisterUseCase = RegisterUseCase(auth, firestore)

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        loginUseCase: LoginUseCase,
        registerUseCase: RegisterUseCase
    ): AuthenticationRepository = AuthenticationRepositoryImpl(loginUseCase, registerUseCase)
}
