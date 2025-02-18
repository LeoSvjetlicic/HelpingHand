package org.volonter.helpinghand.domain.usecases

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import org.volonter.helpinghand.utlis.Constants
import org.volonter.helpinghand.utlis.SharedPreferencesHelper
import org.volonter.helpinghand.utlis.ToastHelper
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val toastHelper: ToastHelper
) {
    suspend fun invoke(
        name: String,
        email: String,
        password: String,
        isOrganisation: Boolean,
        navigate: () -> Unit
    ) {
        try {
            Log.d("RegisterUseCase", "Attempting registration")

            val registrationResult = suspendCancellableCoroutine { continuation ->
                auth.createUserWithEmailAndPassword(email.trim(), password.trim())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val userId = auth.currentUser?.uid
                            if (userId != null) {
                                val user = hashMapOf(
                                    "name" to name,
                                    "email" to email,
                                    "isOrganisation" to isOrganisation,
                                    "imageLink" to "https://upload.wikimedia.org/wikipedia/en/b/b1/Portrait_placeholder.png"
                                )

                                firestore.collection("users")
                                    .document(userId)
                                    .set(user)
                                    .addOnSuccessListener {
                                            continuation.resumeWith(Result.success(true))
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.d(
                                            "Firestore",
                                            "Error saving user: ${exception.message}"
                                        )
                                        toastHelper.createToast(
                                            "Failed to save user data",
                                            Toast.LENGTH_SHORT
                                        )
                                            continuation.resumeWith(Result.success(false))
                                    }
                            } else {
                                Log.d("AuthError", "User ID is null after registration")
                                toastHelper.createToast("Registration failed", Toast.LENGTH_SHORT)
                                    continuation.resumeWith(Result.success(false))
                            }
                        } else {
                            Log.d("AuthError", "Registration failed: ${task.exception?.message}")
                            toastHelper.createToast("Registration failed", Toast.LENGTH_SHORT)
                                continuation.resumeWith(Result.success(false))
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d("AuthError", "Registration error: ${exception.message}")
                        if (continuation.isActive) {
                            continuation.resumeWith(Result.success(false))
                        }
                    }
            }

            if (registrationResult) {
                SharedPreferencesHelper.saveBooleanToPrefs(
                    Constants.SharedPreferencesConstants.SHARED_PREFERENCES_IS_ORGANISATION,
                    isOrganisation
                )
                navigate()
            }
        } catch (e: Throwable) {
            Log.d("Error", "Unexpected error: ${e.message}")
        }
    }
}
