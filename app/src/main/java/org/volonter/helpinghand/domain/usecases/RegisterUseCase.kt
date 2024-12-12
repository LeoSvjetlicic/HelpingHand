package org.volonter.helpinghand.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val auth: FirebaseAuth) {
    fun invoke(name: String, email: String, password: String, isOrganisation: Boolean): Boolean {
        TODO("Not yet implemented")
        return true
    }
}
