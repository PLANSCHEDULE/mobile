package com.example.thirdpj.data.template.dto

data class TemplateResponse(
    val id: Long,
    val title: String,
    val background: String,
    val targetDate: String,
    val items: List<TemplateItemResponse>
)
