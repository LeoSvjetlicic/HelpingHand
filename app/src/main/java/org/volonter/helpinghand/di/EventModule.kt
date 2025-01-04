package org.volonter.helpinghand.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.volonter.helpinghand.data.repository.EventRepositoryImpl
import org.volonter.helpinghand.domain.repository.EventRepository
import org.volonter.helpinghand.domain.usecases.CreateNewEventUseCase
import org.volonter.helpinghand.domain.usecases.GetAllEventsForSearchUseCase
import org.volonter.helpinghand.domain.usecases.GetAllMarkersUseCase
import org.volonter.helpinghand.domain.usecases.GetSupportedCitiesUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventModule {

    @Provides
    @Singleton
    fun provideCreateNewEventUseCase(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): CreateNewEventUseCase = CreateNewEventUseCase(firebaseAuth, firestore)

    @Provides
    @Singleton
    fun provideGetSupportedCitiesUseCase(
        firestore: FirebaseFirestore
    ): GetSupportedCitiesUseCase = GetSupportedCitiesUseCase(firestore)

    @Provides
    @Singleton
    fun provideGetAllMarkersUseCase(
        firestore: FirebaseFirestore
    ): GetAllMarkersUseCase = GetAllMarkersUseCase(firestore)

    @Provides
    @Singleton
    fun provideEventRepository(
        createNewEventUseCase: CreateNewEventUseCase,
        getSupportedCitiesUseCase: GetSupportedCitiesUseCase,
        getAllMarkersUseCase: GetAllMarkersUseCase,
        getAllEventsForSearchUseCase: GetAllEventsForSearchUseCase
    ): EventRepository =
        EventRepositoryImpl(createNewEventUseCase, getSupportedCitiesUseCase, getAllMarkersUseCase, getAllEventsForSearchUseCase)
}
