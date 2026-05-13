package com.example.thirdpj.data.auth.api

import com.example.thirdpj.data.auth.dto.SignUpRequest
import com.example.thirdpj.data.auth.dto.SignUpResponse
import com.example.thirdpj.data.dto.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<ApiResponse<SignUpResponse>>
}