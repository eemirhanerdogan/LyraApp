package com.example.lyraapp.data.auth

interface AuthRepository {
    suspend fun login(phoneNumber: String, password: String): Result<Unit>
    suspend fun register(phoneNumber: String, password: String): Result<Unit>
}
