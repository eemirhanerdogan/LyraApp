package com.example.lyraapp.ui.screen.register

data class RegisterUiState(
    val phoneNumber: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
    val error: String? = null
) {
    val isRegisterEnabled: Boolean
        get() = phoneNumber.isNotBlank() && 
                password.isNotBlank() && 
                confirmPassword.isNotBlank() && 
                !isLoading
}

sealed interface RegisterIntent {
    data class PhoneNumberChanged(val phoneNumber: String) : RegisterIntent
    data class PasswordChanged(val password: String) : RegisterIntent
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterIntent
    data object TogglePasswordVisibility : RegisterIntent
    data object ToggleConfirmPasswordVisibility : RegisterIntent
    data object Submit : RegisterIntent
    data object LoginClicked : RegisterIntent
}

sealed interface RegisterEffect {
    data object NavigateToHome : RegisterEffect
    data object NavigateToLogin : RegisterEffect
    data class ShowError(val message: String) : RegisterEffect
}
