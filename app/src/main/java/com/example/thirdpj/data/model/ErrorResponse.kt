package com.example.thirdpj.data.model

data class ErrorResponse(
    val status: Int,
    val code: String,
    val message: String
)