package com.example.thirdpj.data.auth.dto

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
