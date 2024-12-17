package org.volonter.helpinghand.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class LoginUseCase @Inject constructor(private val auth: FirebaseAuth) {
    suspend fun invoke(email: String, password: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            println("User logged in successfully")
            true
        } catch (e: Exception) {
            println("Error logging in: ${e.message}")
            false
        }
    }
}
