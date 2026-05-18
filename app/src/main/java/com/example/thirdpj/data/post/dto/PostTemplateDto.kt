package com.example.thirdpj.data.post.dto

import com.google.gson.annotations.SerializedName

data class PostTemplateDto(
    val postTemplateId: Long,
    val title: String,
    val background: String?,
    val authorHandle: String,
    val favoriteCount: Int,
    val downloadCount: Int,
    @SerializedName("favorite")
    val isFavorite: Boolean,
    val items: List<PostTemplateItem>

)
