package com.example.thirdpj.data.auth.api

import com.example.thirdpj.data.auth.dto.SignUpRequest
import com.example.thirdpj.data.auth.dto.SignUpResponse
import com.example.thirdpj.data.GlobalDto.ApiResponse
import com.example.thirdpj.data.auth.dto.LoginRequest
import com.example.thirdpj.data.auth.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    // 회원가입
    @POST("/api/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<ApiResponse<SignUpResponse>>

    // 로그인
    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<LoginResponse>>
}