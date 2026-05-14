package com.example.thirdpj.data.repository

import com.example.thirdpj.data.api.AuthService
import com.example.thirdpj.data.auth.dto.LoginRequest
import com.example.thirdpj.data.auth.dto.SignUpRequest

class AuthRepository(private val authService: AuthService) {
    suspend fun login(loginRequest: LoginRequest) = authService.login(loginRequest)

    suspend fun signUp(signUpRequest: SignUpRequest) = authService.signUp(signUpRequest)
}