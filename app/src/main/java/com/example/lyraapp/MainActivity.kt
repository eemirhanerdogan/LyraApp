package com.example.lyraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lyraapp.ui.screen.login.LoginRoute
import com.example.lyraapp.ui.theme.LyraAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LyraAppTheme {
                LoginRoute(
                    onNavigateToHome = { /* Navigasyon henüz kurulmadı */ },
                    onNavigateToRegister = { /* Navigasyon henüz kurulmadı */ }
                )
            }
        }
    }
}
