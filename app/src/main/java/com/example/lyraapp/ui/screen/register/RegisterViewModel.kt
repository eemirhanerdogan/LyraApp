package com.example.lyraapp.ui.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lyraapp.data.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state.asStateFlow()

    private val _effect = Channel<RegisterEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.PhoneNumberChanged -> {
                _state.update { it.copy(phoneNumber = intent.phoneNumber, error = null) }
            }
            is RegisterIntent.PasswordChanged -> {
                _state.update { it.copy(password = intent.password, error = null) }
            }
            is RegisterIntent.ConfirmPasswordChanged -> {
                _state.update { it.copy(confirmPassword = intent.confirmPassword, error = null) }
            }
            RegisterIntent.TogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            RegisterIntent.ToggleConfirmPasswordVisibility -> {
                _state.update { it.copy(isConfirmPasswordVisible = !it.isConfirmPasswordVisible) }
            }
            RegisterIntent.Submit -> {
                register()
            }
            RegisterIntent.LoginClicked -> {
                viewModelScope.launch {
                    _effect.send(RegisterEffect.NavigateToLogin)
                }
            }
        }
    }

    private fun register() {
        val currentState = _state.value
        if (!currentState.isRegisterEnabled) return

        if (currentState.password != currentState.confirmPassword) {
            viewModelScope.launch {
                _effect.send(RegisterEffect.ShowError("Şifreler eşleşmiyor"))
            }
            return
        }

        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            authRepository.register(currentState.phoneNumber, currentState.password)
                .onSuccess {
                    _state.update { it.copy(isLoading = false) }
                    _effect.send(RegisterEffect.NavigateToHome)
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, error = error.message) }
                    _effect.send(RegisterEffect.ShowError(error.message ?: "Kayıt sırasında bir hata oluştu"))
                }
        }
    }
}
