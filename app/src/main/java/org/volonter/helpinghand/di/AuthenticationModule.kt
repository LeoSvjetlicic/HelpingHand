package org.volonter.helpinghand.di

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
        firebase: FirebaseFirestore
    ): LoginUseCase = LoginUseCase(firebase)

    @Provides
    @Singleton
    fun provideRepositoryUseCase(
        firebase: FirebaseFirestore
    ): RegisterUseCase = RegisterUseCase(firebase)

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        loginUseCase: LoginUseCase,
        registerUseCase: RegisterUseCase
    ): AuthenticationRepository = AuthenticationRepositoryImpl(loginUseCase, registerUseCase)
}
