package com.example.thirdpj.data.repository

import com.example.thirdpj.data.api.AuthService
import com.example.thirdpj.data.auth.dto.LoginRequest
import com.example.thirdpj.data.auth.dto.SignUpRequest

class AuthRepository(private val authService: AuthService) {
    suspend fun login(loginRequest: LoginRequest) = authService.login(loginRequest)
    // suspend가 붙은 이유는 코루틴과 같은 비동기 처리가 가능한 환경에서 실행해 달라 표시하는 것과 같음,
    // 네트워크 응답이 올 때 코루틴만 멈춰줌.
    // 안그러면 화면을 그리는 메인 스레드에서 멈춤 현상이 생김
    suspend fun signUp(signUpRequest: SignUpRequest) = authService.signUp(signUpRequest)
}