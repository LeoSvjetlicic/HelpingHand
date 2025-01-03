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
import org.volonter.helpinghand.domain.usecases.LogoutUseCase
import org.volonter.helpinghand.domain.usecases.RegisterUseCase
import org.volonter.helpinghand.utlis.ToastHelper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(
        auth: FirebaseAuth,
        toastHelper: ToastHelper
    ): LoginUseCase = LoginUseCase(auth, toastHelper)

    @Provides
    @Singleton
    fun provideRepositoryUseCase(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
        toastHelper: ToastHelper
    ): RegisterUseCase = RegisterUseCase(auth, firestore, toastHelper)

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        loginUseCase: LoginUseCase,
        registerUseCase: RegisterUseCase,
        logoutUseCase: LogoutUseCase
    ): AuthenticationRepository = AuthenticationRepositoryImpl(loginUseCase, registerUseCase, logoutUseCase)
}
