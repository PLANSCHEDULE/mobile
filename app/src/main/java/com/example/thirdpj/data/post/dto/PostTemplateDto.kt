package com.example.thirdpj.data.post.dto

data class PostTemplateDto(
    val postTemplateId: Long,
    val title: String,
    val background: String?,
    val authorName: String,
    val authorHandle: String,
    val favoriteCount: Int,
    val downloadCount: Int,
    val isFavorite: Boolean,
    val items: List<PostTemplateItem>

)
