package com.example.thirdpj.ui.data

data class PlanItem(
    val id: Int,
    val time: String,
    val title: String,
    var isDone: Boolean = false
)