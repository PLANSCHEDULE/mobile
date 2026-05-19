package com.example.thirdpj.data.template.dto

data class TemplateUpdateItem(
    val time: String,
    val content: String,
    val sequence: Int,
    val isCompleted: Boolean,
    val isAlarmOn: Boolean

)
