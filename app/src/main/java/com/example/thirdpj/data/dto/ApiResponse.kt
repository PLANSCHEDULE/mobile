package com.example.thirdpj.data.dto

// 서버 공통응답 대응 dto
data class ApiResponse<T>(
    val status: Int,
    val message: String,
    val data: T?,
    val timestamp: String,
    val path: String
)
