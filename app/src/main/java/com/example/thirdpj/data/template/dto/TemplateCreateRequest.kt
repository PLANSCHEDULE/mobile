package com.example.thirdpj.data.template.dto

import com.example.thirdpj.data.template.dto.TemplateItemDto

data class TemplateCreateRequest(
    val title: String,
    val background: String,
    val targetDate: String,
    val items: List<TemplateItemDto>
)