package com.example.lyraapp.ui.screen.home

data class HomeUiState(
    val title: String = "LyraApp",
    val welcomeMessage: String = "Müzik dünyasına hoş geldin!",
    val isLoading: Boolean = false
)

sealed interface HomeIntent {
    data object RefreshClicked : HomeIntent
    data object LogoutClicked : HomeIntent
}

sealed interface HomeEffect {
    data object NavigateToLogin : HomeEffect
    data class ShowMessage(val message: String) : HomeEffect
}
