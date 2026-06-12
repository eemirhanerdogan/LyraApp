package com.example.lyraapp.ui.screen.login

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lyraapp.ui.theme.LyraAppTheme

@Composable
fun LoginRoute(
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = SnackbarHostState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is LoginEffect.NavigateToHome -> onNavigateToHome()
                is LoginEffect.NavigateToRegister -> onNavigateToRegister()
                is LoginEffect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        LoginScreen(
            modifier = Modifier.padding(paddingValues),
            state = state,
            onIntent = viewModel::onIntent
        )
    }
}

@Composable
fun LoginScreen(
    state: LoginUiState,
    onIntent: (LoginIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .statusBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // App Icon Placeholder
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primaryContainer,
                                MaterialTheme.colorScheme.tertiaryContainer
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(4) { index ->
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(if (index % 2 == 0) 24.dp else 36.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(MaterialTheme.colorScheme.onPrimaryContainer)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Tekrar hoş geldin",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Hesabına giriş yap, kaldığın yerden dinlemeye devam et.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Phone Number Field
            OutlinedTextField(
                value = state.phoneNumber,
                onValueChange = { onIntent(LoginIntent.PhoneNumberChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Telefon numarası") },
                placeholder = { Text("+90  5XX XXX XX XX") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = null)
                },
                shape = RoundedCornerShape(16.dp),
                enabled = !state.isLoading,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            OutlinedTextField(
                value = state.password,
                onValueChange = { onIntent(LoginIntent.PasswordChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Şifre") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                },
                trailingIcon = {
                    // Password visibility toggle removed to avoid extended icons dependency
                    TextButton(onClick = { onIntent(LoginIntent.TogglePasswordVisibility) }) {
                        Text(
                            text = if (state.isPasswordVisible) "Gizle" else "Göster",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                },
                visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(16.dp),
                enabled = !state.isLoading,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = { /* Şifremi unuttum logic */ },
                modifier = Modifier.align(Alignment.End),
                enabled = !state.isLoading
            ) {
                Text(
                    text = "Şifremi unuttum",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Login Button
            Button(
                onClick = { onIntent(LoginIntent.Submit) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                enabled = state.isLoginEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.isLoginEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                    contentColor = if (state.isLoginEnabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                )
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Giriş yap",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Footer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hesabın yok mu?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                TextButton(
                    onClick = { onIntent(LoginIntent.RegisterClicked) },
                    enabled = !state.isLoading
                ) {
                    Text(
                        text = "Kayıt ol",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun LoginScreenPreviewLight() {
    LyraAppTheme(darkTheme = false) {
        LoginScreen(
            state = LoginUiState(phoneNumber = "5551234567"),
            onIntent = {}
        )
    }
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreviewDark() {
    LyraAppTheme(darkTheme = true) {
        LoginScreen(
            state = LoginUiState(isLoading = true),
            onIntent = {}
        )
    }
}
