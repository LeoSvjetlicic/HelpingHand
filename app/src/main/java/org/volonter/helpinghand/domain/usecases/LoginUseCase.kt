package org.volonter.helpinghand.domain.usecases

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import org.volonter.helpinghand.utlis.ToastHelper
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val auth: FirebaseAuth,
    private val toastHelper: ToastHelper
) {
    suspend fun invoke(email: String, password: String): Boolean {
        if (email.isEmpty() || password.isEmpty()) {
            toastHelper.createToast("Elements can't be empty", Toast.LENGTH_SHORT)
            return false
        }

        return try {
            suspendCancellableCoroutine { continuation ->
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
        } catch (e: Throwable) {
            Log.d("error", e.message.toString())
            false
        }
    }
}
