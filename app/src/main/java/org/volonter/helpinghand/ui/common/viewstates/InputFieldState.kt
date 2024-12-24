package org.volonter.helpinghand.ui.common.viewstates

import androidx.annotation.StringRes

data class InputFieldState(
    val value: String = "",
    val error: String = "",
    @StringRes val label: Int = 0,
)
