package org.volonter.helpinghand.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.volonter.helpinghand.data.repository.ReviewRepositoryImpl
import org.volonter.helpinghand.domain.repository.ReviewRepository
import org.volonter.helpinghand.domain.usecases.AddReviewUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReviewModule {

    @Provides
    @Singleton
    fun provideAddReviewUseCase(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AddReviewUseCase = AddReviewUseCase(firebaseAuth, firestore)

    @Provides
    @Singleton
    fun provideReviewRepository(addReviewUseCase: AddReviewUseCase): ReviewRepository =
        ReviewRepositoryImpl(addReviewUseCase)
}