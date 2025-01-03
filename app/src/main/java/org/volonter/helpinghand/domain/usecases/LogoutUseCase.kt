package org.volonter.helpinghand.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import org.volonter.helpinghand.utlis.SharedPreferencesHelper
import org.volonter.helpinghand.utlis.ToastHelper
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val auth: FirebaseAuth,
    private val toastHelper: ToastHelper
) {
    operator fun invoke(navigate: () -> Unit) {
        try {
            auth.signOut()
            SharedPreferencesHelper.clearSharedPrefs()
            navigate()
        } catch (e: Throwable) {
            toastHelper.createToast("Unexpected error: ${e.message}", 0)
        }
    }
}
