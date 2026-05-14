package com.example.thirdpj.ui.testdata

import androidx.compose.ui.graphics.Color

data class TemplateItemData(
    val id:Int,
    val title: String,
    val authorName: String,
    val authorHandle: String,
    val likeCount: String,
    val downloadCount: String,
    val schedules: List<Pair<String, String>>,
    val themeColor: Color = Color(0xFFFFB380)
)
