package com.example.lyraapp.ui.screen.login

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
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.PhoneNumberChanged -> {
                _state.update { it.copy(phoneNumber = intent.phoneNumber, error = null) }
            }
            is LoginIntent.PasswordChanged -> {
                _state.update { it.copy(password = intent.password, error = null) }
            }
            LoginIntent.TogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            LoginIntent.Submit -> {
                login()
            }
            LoginIntent.RegisterClicked -> {
                viewModelScope.launch {
                    _effect.send(LoginEffect.NavigateToRegister)
                }
            }
        }
    }

    private fun login() {
        val currentState = _state.value
        if (!currentState.isLoginEnabled) return

        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            authRepository.login(currentState.phoneNumber, currentState.password)
                .onSuccess {
                    _state.update { it.copy(isLoading = false) }
                    _effect.send(LoginEffect.NavigateToHome)
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, error = error.message) }
                    _effect.send(LoginEffect.ShowError(error.message ?: "Bir hata oluştu"))
                }
        }
    }
}
