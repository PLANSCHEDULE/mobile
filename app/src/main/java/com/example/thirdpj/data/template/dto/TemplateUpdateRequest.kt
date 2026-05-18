package com.example.thirdpj.data.template.dto

data class TemplateUpdateRequest(
    val title: String,
    val targetDate: String,
    val items: List<TemplateUpdateItem>
)
