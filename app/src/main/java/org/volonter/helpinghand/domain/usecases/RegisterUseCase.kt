package org.volonter.helpinghand.domain.usecases

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val firestore: FirebaseFirestore) {
    fun invoke(email: String, password: String) {
        TODO("Not yet implemented")
    }
}
