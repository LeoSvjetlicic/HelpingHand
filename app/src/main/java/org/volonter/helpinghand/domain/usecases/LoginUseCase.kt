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

class LoginUseCase @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val toastHelper: ToastHelper
) {
    suspend fun invoke(email: String, password: String, navigate: () -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            toastHelper.createToast("Elements can't be empty", Toast.LENGTH_SHORT)
            return
        }

        try {
            val loginResult = suspendCancellableCoroutine { continuation ->
                auth.signInWithEmailAndPassword(email.trim(), password.trim())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                                continuation.resumeWith(Result.success(true))
                        } else {
                            toastHelper.createToast("Login failed", Toast.LENGTH_SHORT)
                                continuation.resumeWith(Result.success(false))
                        }
                    }.addOnFailureListener { exception ->
                        Log.d("error", exception.message.toString())
                            continuation.resumeWith(Result.success(false))
                    }
            }

            if (loginResult) {
                val userId = auth.uid
                userId?.let {
                    firestore.collection("users")
                        .document(it)
                        .get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                val isOrganisation = document.getBoolean("isOrganisation") ?: false
                                SharedPreferencesHelper.saveBooleanToPrefs(
                                    Constants.SharedPreferencesConstants.SHARED_PREFERENCES_IS_ORGANISATION,
                                    isOrganisation
                                )
                            }
                        }
                }
                navigate()
            }
        } catch (e: Throwable) {
            Log.d("error", "Unexpected error: ${e.message}")
        }
    }
}
