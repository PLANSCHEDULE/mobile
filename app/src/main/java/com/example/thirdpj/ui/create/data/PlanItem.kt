package com.example.thirdpj.ui.create.data

data class PlanItem(
    val id: Int,
    val time: String,
    val title: String,
    val emoji: String,
    var isDone: Boolean = false
)