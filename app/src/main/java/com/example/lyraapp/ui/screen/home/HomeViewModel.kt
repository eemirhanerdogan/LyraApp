package com.example.lyraapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    private val _effect = Channel<HomeEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.RefreshClicked -> refresh()
            HomeIntent.LogoutClicked -> {
                viewModelScope.launch {
                    _effect.send(HomeEffect.NavigateToLogin)
                }
            }
        }
    }

    private fun refresh() {
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            delay(2000) // Fake loading simulation
            _state.update {
                it.copy(
                    isLoading = false,
                    welcomeMessage = "Müzik dünyasına tekrar hoş geldin! Veriler güncellendi."
                )
            }
            _effect.send(HomeEffect.ShowMessage("Veriler başarıyla yenilendi."))
        }
    }
}
