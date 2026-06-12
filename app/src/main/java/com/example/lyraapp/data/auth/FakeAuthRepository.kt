package com.example.lyraapp.data.auth

import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeAuthRepository @Inject constructor() : AuthRepository {

    override suspend fun login(phoneNumber: String, password: String): Result<Unit> {
        delay(1500) // Sahte ağ gecikmesi
        return if (phoneNumber.isBlank() || password.isBlank()) {
            Result.failure(Exception("Telefon numarası veya şifre boş olamaz"))
        } else {
            Result.success(Unit)
        }
    }

    override suspend fun register(phoneNumber: String, password: String): Result<Unit> {
        delay(1500) // Sahte ağ gecikmesi
        return if (phoneNumber.isBlank() || password.isBlank()) {
            Result.failure(Exception("Telefon numarası veya şifre boş olamaz"))
        } else {
            Result.success(Unit)
        }
    }
}
