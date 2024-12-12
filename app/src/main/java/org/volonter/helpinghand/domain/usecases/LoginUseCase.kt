package org.volonter.helpinghand.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val auth: FirebaseAuth) {
    fun invoke(email: String, password: String): Boolean =
        auth.signInWithEmailAndPassword(email, password).isSuccessful
}
