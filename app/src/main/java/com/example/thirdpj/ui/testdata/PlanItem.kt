package com.example.thirdpj.ui.testdata

import java.time.LocalTime

data class PlanItem(
    val id: Int,
    val time: LocalTime,
    val title: String,
    var isDone: Boolean = false
)