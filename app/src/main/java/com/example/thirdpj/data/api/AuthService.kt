package com.example.thirdpj.data.api

import com.example.thirdpj.data.model.globaldto.ApiResponse
import com.example.thirdpj.data.auth.dto.LoginRequest
import com.example.thirdpj.data.auth.dto.LoginResponse
import com.example.thirdpj.data.auth.dto.RefreshRequest
import com.example.thirdpj.data.auth.dto.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    // 회원가입
    @POST("api/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<ApiResponse<LoginResponse>>

    // 로그인
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<LoginResponse>>

    // accessToken 재발급
    @POST("api/auth/refresh")
    suspend fun refreshToken(
        @Body request: RefreshRequest
    ): Response<ApiResponse<LoginResponse>>

    @POST("api/auth/logout")
    suspend fun logout(): Response<ApiResponse<Unit>>
}