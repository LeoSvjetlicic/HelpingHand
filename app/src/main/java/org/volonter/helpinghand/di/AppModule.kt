package org.volonter.helpinghand.di

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.leosvjetlicic.calendarlibrary.utils.Selected
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.volonter.helpinghand.ui.screens.addevent.components.calendar.CalendarHelper
import org.volonter.helpinghand.utlis.ToastHelper
import java.time.DayOfWeek
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebase(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideToastHelper(
        @ApplicationContext context: Context
    ): ToastHelper =
        ToastHelper(context)

    @Provides
    @Singleton
    fun provideCalendarHelper(): CalendarHelper = CalendarHelper(
        listOf(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
        )
    )

    @Provides
    @Singleton
    fun provideSelected(): Selected = Selected.DayRange(null, null)
}
