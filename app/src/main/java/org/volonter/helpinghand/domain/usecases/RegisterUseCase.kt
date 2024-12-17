package org.volonter.helpinghand.domain.usecases

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.volonter.helpinghand.data.model.User
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class RegisterUseCase @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun invoke(name: String, email: String, password: String, isOrganisation: Boolean): Boolean {
        return try {
            // Create the user in Firebase Authentication
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()

            val uid = authResult.user?.uid
            if (uid == null) {
                Log.e("RegisterUseCase", "Failed to register user: User ID is null")
                return false
            }

            // Create user object
            val user = User(
                username = name,
                city = "",
                id = uid,
                imageLink = "",
                organization = isOrganisation
            )

            // Save the user in Firestore
            firestore.collection("users").document(uid).set(user).await()

            Log.d("RegisterUseCase", "User registered successfully")
            true
        } catch (e: Exception) {
            Log.e("RegisterUseCase", "Failed to register user", e)
            false
        }
    }
}
