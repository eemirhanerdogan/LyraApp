package com.example.lyraapp.ui.screen.login

data class LoginUiState(
    val phoneNumber: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val error: String? = null
) {
    val isLoginEnabled: Boolean
        get() = phoneNumber.isNotBlank() && password.isNotBlank() && !isLoading
}

sealed interface LoginIntent {
    data class PhoneNumberChanged(val phoneNumber: String) : LoginIntent
    data class PasswordChanged(val password: String) : LoginIntent
    data object TogglePasswordVisibility : LoginIntent
    data object Submit : LoginIntent
    data object RegisterClicked : LoginIntent
}

sealed interface LoginEffect {
    data object NavigateToHome : LoginEffect
    data object NavigateToRegister : LoginEffect
    data class ShowError(val message: String) : LoginEffect
}
