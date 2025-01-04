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
import org.volonter.helpinghand.domain.usecases.GetEventByIdUseCase
import org.volonter.helpinghand.domain.usecases.GetReviewUseCase
import org.volonter.helpinghand.domain.usecases.GetSupportedCitiesUseCase
import org.volonter.helpinghand.domain.usecases.GetUserDetailsUseCase
import org.volonter.helpinghand.domain.usecases.ToggleApplicationButtonUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventModule {

    @Provides
    @Singleton
    fun provideUserDetailsUseCase(
        firestore: FirebaseFirestore
    ): GetUserDetailsUseCase = GetUserDetailsUseCase(firestore)

    @Provides
    @Singleton
    fun provideToggleApplicationButtonUseCase(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): ToggleApplicationButtonUseCase = ToggleApplicationButtonUseCase(auth, firestore)

    @Provides
    @Singleton
    fun provideGetReviewUseCase(
        firestore: FirebaseFirestore,
        getUserDetailsUseCase: GetUserDetailsUseCase
    ): GetReviewUseCase = GetReviewUseCase(firestore, getUserDetailsUseCase)

    @Provides
    @Singleton
    fun provideGetEventByIdUseCase(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
        getOrganisationDetailsUseCase: GetUserDetailsUseCase,
        getReviewUseCase: GetReviewUseCase
    ): GetEventByIdUseCase = GetEventByIdUseCase(
        auth,
        firestore,
        getOrganisationDetailsUseCase,
        getReviewUseCase
    )

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
        getEventByIdUseCase: GetEventByIdUseCase,
        toggleApplicationButtonUseCase: ToggleApplicationButtonUseCase
        getAllMarkersUseCase: GetAllMarkersUseCase,
        getAllEventsForSearchUseCase: GetAllEventsForSearchUseCase
    ): EventRepository =
        EventRepositoryImpl(
            createNewEventUseCase,
            getSupportedCitiesUseCase,
            getAllMarkersUseCase,
            getEventByIdUseCase,
            toggleApplicationButtonUseCase,
            getAllEventsForSearchUseCase
        )
}
