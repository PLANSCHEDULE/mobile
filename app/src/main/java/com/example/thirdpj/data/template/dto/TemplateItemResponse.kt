package com.example.thirdpj.data.template.dto

import com.google.gson.annotations.SerializedName

data class TemplateItemResponse(
    val id: Long,
    val itemTime: String,
    val content: String,
    val sequence: Int,
    @SerializedName("alarmOn")
    val isAlarmOn: Boolean,
    @SerializedName("completed")
    val isCompleted: Boolean
)